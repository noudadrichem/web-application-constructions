'use strict'

window.addEventListener('DOMContentLoaded',() => {

  const buttons = document.querySelectorAll('.btn')
  const displays = document.querySelectorAll('.display')

  function resetDisplay() {
    displays[0].innerHTML = ''
  }

  function fillDisplayWithValue(value) {
    displays[0].innerText = `${displays[0].innerText}${value}`
  }

  buttons.forEach(btn => {
    btn.addEventListener('click', e => {
      const currentPressedValue = btn.dataset.value

      if(currentPressedValue === 'C') {
        resetDisplay()
      } else if (currentPressedValue === '=') {
        displays[1].innerText = eval(displays[0].innerText)
        resetDisplay()
      } else {
        fillDisplayWithValue(currentPressedValue)
      }
    })
  })


  // not important code here: 
  const toggle = document.querySelector('.theme-switch input[type="checkbox"]');
  toggle.addEventListener('change', (e) => {
    if (e.target.checked) {
        document.documentElement.setAttribute('data-theme', 'dark');
    } else {
        document.documentElement.setAttribute('data-theme', 'light');
    }    
  }, false);

})