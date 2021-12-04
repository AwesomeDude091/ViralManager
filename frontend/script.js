'use strict'
const sidebar = document.querySelector('.sidebar')
const closeBtn = document.querySelector('#btn')
const allTabs = document.getElementsByClassName('tab')
const allContent = document.getElementsByClassName('actual-content')

// following are the code to change sidebar button(optional)
function menuBtnChange () {
  if (sidebar.classList.contains('open')) {
    closeBtn.classList.replace('bx-menu', 'bx-menu-alt-right') // replacing the icons class
  } else {
    closeBtn.classList.replace('bx-menu-alt-right', 'bx-menu') // replacing the icons class
  }
}

function tabChange (element) {
  let elementID = -1
  for (let i = 0; i < allTabs.length; i++) {
    if (allTabs[i] === element) {
      elementID = i
      break
    }
  }

  if (elementID === -1) {
    return
  }

  for (let k = 0; k < allTabs.length; k++) {
    if (allTabs[k].classList.contains('active-tab')) {
      if (k === elementID) {
        return
      }
    }
  }

  for (let j = 0; j < allTabs.length; j++) {
    if (allTabs[j].classList.contains('active-tab')) {
      allTabs[j].classList.replace('active-tab', 'inactive-tab')
      allContent[j].classList.replace('active-content', 'inactive-content')
    } else if (j === elementID) {
      allTabs[j].classList.replace('inactive-tab', 'active-tab')
      allContent[j].classList.replace('inactive-content', 'active-content')
    }
  }
}

function assignListener (element) {
  element.addEventListener('click', () => {
    tabChange(element)
  })
}

closeBtn.addEventListener('click', () => {
  sidebar.classList.toggle('open')
  menuBtnChange() // calling the function(optional)
})

allTabs[0].classList.add('active-tab')
allContent[0].classList.add('active-content')
assignListener(allTabs[0])

for (let i = 1; i < allTabs.length; i++) {
  allTabs[i].classList.add('inactive-tab')
  allContent[i].classList.add('inactive-content')
  assignListener(allTabs[i])
}
