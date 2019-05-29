'use strict'

const WEATHER_API_KEY = '742a2e3e80a51ac3c2cd1b358b7550f7'
const headers = {
	'Authorization': 'Bearer ' + window.localStorage.getItem('TOK')
}

const el = node => document.querySelector(node)

function secondsToHourMinuteSecond(totalSeconds) {
  const hour = Math.floor(totalSeconds / 3600)
  const minute = Math.floor(totalSeconds % 3600 / 60)
  const seconds = Math.floor(totalSeconds % 3600 % 60)

  return `${('0' + hour).slice(-2)}:${('0' + minute).slice(-2)}:${('0' + seconds).slice(-2)}`
}

function getWindrichting(num) {
	const val =  Math.floor((num / 45) + 0.5);
	const arr = ["N","NE","E", "SE","S","SW","W","NW"];
	return arr[(val % 8)]
}

function getTimePlusZone(timestamp) {
  const date = new Date(timestamp * 1000)
  const hours = date.getHours()
  const minutes = `0${date.getMinutes()}`
  const seconds = `0${date.getSeconds()}`

  return hours + ':' + minutes.substr(-2) + ':' + seconds.substr(-2)
}


function fetchWeatherData(lat, long, city) {
	const now = new Date().getTime();
	const isThereInStorage = window.localStorage.getItem(`weather-${city}`)
	
	return new Promise((res) => {
		if(isThereInStorage) {
			const { updated, weatherData } = JSON.parse(isThereInStorage)
			const isItStillValid = new Date(updated + (1 * 60000)) >= updated
			console.log({ isItStillValid })
			
			res(weatherData)
		} else {

	    fetch(`https://api.openweathermap.org/data/2.5/weather?lat=${lat}&lon=${long}&appid=${WEATHER_API_KEY}&units=metric`)
		    .then(res => res.json())
		    .then(weatherData => {
	        window.localStorage.setItem(`weather-${city}`, JSON.stringify({ weatherData, updated: new Date().getTime() }))

			    res(weatherData)
		    })
		    .catch(err => console.log({ err }))
			
		}	
	})
}	
	
	


function showWeather(lat, long, city) {
  const huidigeStadNode = el('#huidige-stad')
  huidigeStadNode.innerText = city || 'geen stad gevonden'
  const weatherContainerNode = el('#weather-info ul')
  weatherContainerNode.innerHTML = ''

  fetchWeatherData(lat, long, city)
    .then(data => {
      const renderData = {
        luchtvochtigheid: data.main.humidity,
        temperatuur: data.main.temp,
        windrichting: getWindrichting(data.wind.deg),
        windsnelheid: data.wind.speed,
        zonsopgang: getTimePlusZone(data.sys.sunrise),
        zonsondergang: getTimePlusZone(data.sys.sunset),
      }

      const imgTag = `<img src='https://openweathermap.org/img/w/${data.weather[0].icon}.png'/>`
      document.querySelector(".weather-img").innerHTML = imgTag;

      Object.keys(renderData)
        .forEach((key) => {
          const value = renderData[key]
          weatherContainerNode.innerHTML += `
            <li>${key} : <span>${value}</span></li>
          `
        })
    })
}

function insertLocationDataInDom(locationData) {
  const locationListNode = el('#my-location ul')
  const { city, latitude, longitude, country } = locationData
  showWeather(latitude, longitude, city)

  const flagNode = el('#flag')
  flagNode.className = `flag-icon flag-icon-${country.toLowerCase()}`

  Object.keys(locationData)
    .forEach(key => {
      const value = locationData[key]

      locationListNode.innerHTML += `
        <li>${key.replace(/_/g, ' ')}: <span>${value}</span></li>
      `
    })
}

function renderCountryDataToDom(data) {
  const countryContainer = el('#country-list table')
  countryContainer.innerHTML = ''
  data
  	.sort()
    .forEach(country => {
      const { name, capital, region, surface, population, lat, lng, code } = country
      countryContainer.innerHTML += `
        <tr>
          <td>
            <button class="btn-remove-country" data-code="${code}">x</button>
            <button class="btn-update-country" data-code="${code}" data-country='${JSON.stringify(country)}'>UPD</button>
          </td>
          <td class="land" data-lat="${lat}" data-lon="${lng}" data-city="${capital}">
            ${name}
          </td>
          <td>${capital}</td>
          <td>${region}</td>
          <td>${surface}</td>
          <td>${population}</td>
        </tr>
      `
    })

    return data
}

function getCountryData() {
  fetch('restservices/countries/', { headers })
    .then(res => res.json())
    .then(data => {
      renderCountryDataToDom(data)
      return data
    })
    .then(data => {
      document.querySelectorAll('.land')
        .forEach(btn => {
          btn.addEventListener('click', e => {
            const { lat, lon, city } = e.target.dataset
            showWeather(lat, lon, city)
          })
        })
        
      document.querySelectorAll('.btn-remove-country')
        .forEach(btn => {
          btn.addEventListener('click', e => {
            fetch(`restservices/countries/delete/${e.target.dataset.code}`, { method: 'DELETE', headers })
              .then(stream => stream.json())
              .then(response => {
                console.log({ response })
                getCountryData(data)
              })
          })
        })

      document.querySelectorAll('.btn-update-country')
        .forEach(btn => {
          btn.addEventListener('click', e => {
            document.querySelector("#update-modal").style.display = 'flex'
            const form = document.querySelector('#update-country-form')
            const {
              name,
              capital,
              region,
              surface,
              population,
              code
            } = JSON.parse(e.target.dataset.country);

            console.log({name, code })

            form.land_in.value = name;
            form.hoofdstad_in.value = capital
            form.regio_in.value = region
            form.oppervlakte_in.value = surface
            form.inwoners_in.value = population
            form.btn.dataset.code = code
          })
        })
    })
}

function updateCountryFromModal() {
  const btn = document.querySelector('#update-country')

  btn.addEventListener('click', e => {
    console.log('click update')

    const form = document.querySelector('#update-country-form')
    const formData = new FormData(form);
    const encData = new URLSearchParams(formData.entries());

    console.log('asdfasdf: ', e.target)
    fetch(`restservices/countries/update/${e.target.dataset.code}`, { method: 'PUT', headers, body: encData, })
      .then(response => {
        if(response.ok) {
          return response
        } else {
          throw "nee";
        }
      })
      .then(response => {
        console.log({ response })
      })
  })
}

function initLocationApi() {
  fetch('https://ipapi.co/json')
    .then(res => res.json())
    .then(insertLocationDataInDom)
    .catch(err => console.log({ err }))
}

window.addEventListener('load', function() {
  initLocationApi()
  getCountryData()
  updateCountryFromModal()
  console.log('ja toch niet dan')

  document.querySelector('#close-country').onclick = e => document.querySelector("#update-modal").style.display = 'none'
})
