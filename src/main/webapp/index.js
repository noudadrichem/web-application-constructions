'use strict'

// const WEATHER_API_KEY = '3d0ff603d5b7991d41b50c4095e798b8'
const WEATHER_API_KEY = '00fb0158113e4b223c8064f484b43ac2' // test key van de website werkt wel.

const el = node => document.querySelector(node)

function secondsToHourMinuteSecond(totalSeconds) {
  const hour = Math.floor(totalSeconds / 3600)
  const minute = Math.floor(totalSeconds % 3600 / 60)
  const seconds = Math.floor(totalSeconds % 3600 % 60)

  return `${('0' + hour).slice(-2)}:${('0' + minute).slice(-2)}:${('0' + seconds).slice(-2)}`
}

function getWindrichting(richtingDeg) {
  switch(true) {
    case (richtingDeg > 305 && richtingDeg < 45):
      return 'noord'
    case (richtingDeg > 45 && richtingDeg < 135):
      return 'oost'
    case (richtingDeg > 135 && richtingDeg < 270):
      return 'zuid'
    case (richtingDeg > 270 && richtingDeg < 305):
      return 'west'
  }
}

function getTimePlusZone(timestamp) {
  const date = new Date(timestamp * 1000)
  const hours = date.getHours()
  const minutes = `0${date.getMinutes()}`
  const seconds = `0${date.getSeconds()}`

  return hours + ':' + minutes.substr(-2) + ':' + seconds.substr(-2)
}


function showWeather(lat, long, city) {
  console.log('show weather', lat, long, city)
  const huidigeStadNode = el('#huidige-stad')
  huidigeStadNode.innerText = city || 'geen stad gevonden'
  const weatherContainerNode = el('#weather-info ul')

  fetch(`https://api.openweathermap.org/data/2.5/weather?lat=${lat}&lon=${long}&appid=${WEATHER_API_KEY}&units=metric`)
    .then(res => res.json())
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
  console.log({ locationData  })
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

function getCountryData() {
  fetch('http://localhost:8080/calcu/restservices/countries/')
    .then(res => res.json())
    .then(renderCountryDataToDom)
}

function renderCountryDataToDom(data) {
  const countryContainer = el('#country-list table')

  console.log({countryContainer})

  data
    .forEach(country => {
      const { name, capital, region, surface, population } = country
      countryContainer.innerHTML += `
        <tr>
          <td>${name}</td>
          <td>${capital}</td>
          <td>${region}</td>
          <td>${surface}</td>
          <td>${population}</td>
        </tr>
      `
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
})