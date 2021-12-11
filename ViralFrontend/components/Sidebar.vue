<template>
  <div class="sidebar">
    <div class="logo-details">
      <img class="icon" src="assets/Viral Innovation Logo BIG.png" alt="logo">
      <div class="logo_name">A-Console</div>
      <i class='bx bx-menu' id="btn" ></i>
    </div>
    <ul class="nav-list">
      <li>
        <a href="#">
          <i class='bx bx-grid-alt'></i>
          <span class="links_name">Dashboard</span>
        </a>
        <span class="tooltip">Dashboard</span>
      </li>
      <li>
        <a href="Default-server-Template.html">
          <i class='bx bx-server' ></i>
          <span class="links_name">Physical Servers</span>
        </a>
        <span class="tooltip">Physical Servers</span>
      </li>
      <li>
        <a href="Default-virtual-Template.html">
          <i class='bx bx-cloud' ></i>
          <span class="links_name">Virtual Servers</span>
        </a>
        <span class="tooltip">Virtual Servers</span>
      </li>
      <li>
        <a href="#">
          <i class='bx bx-pie-chart-alt-2' ></i>
          <span class="links_name">Analytics</span>
        </a>
        <span class="tooltip">Analytics</span>
      </li>
      <li>
        <a href="#">
          <i class='bx bx-plug' ></i>
          <span class="links_name">Plugins</span>
        </a>
        <span class="tooltip">Plugins</span>
      </li>
      <li>
        <a href="#">
          <i class='bx bx-user' ></i>
          <span class="links_name">User Settings</span>
        </a>
        <span class="tooltip">User Settings</span>
      </li>
      <li>
        <a href="#">
          <i class='bx bx-cog' ></i>
          <span class="links_name">Settings</span>
        </a>
        <span class="tooltip">Settings</span>
      </li>
      <li class="profile">
        <div class="profile-details">
          <img src="assets/placeholder.jpeg" alt="profileImg">
          <div class="name_job">
            <div class="name">AwesomeDude</div>
            <div class="job">Admin</div>
          </div>
        </div>
        <i class='bx bx-log-out' id="log_out" ></i>
      </li>
    </ul>
  </div>
</template>
<script>
export default {
  name: "Sidebar",
  head: {
    title: 'Viral Manager',
    meta: [
      {charset: 'utf-8'},
      {name: 'viewport', content: 'width=device-width, initial-scale=1'}
    ],
    link: [
      {rel: 'stylesheet', href: 'https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css'}
    ]
  },
  mounted() {
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
  }
}
</script>
<style scoped>
  @import url('https://fonts.googleapis.com/css?family=Poppins:200,300,400,500,600,700,800,900&display=swap');

  *
  {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
    font-family: 'Poppins', sans-serif;
    scroll-behavior: smooth;
  }
  .sidebar{
    position: fixed;
    left: 0;
    top: 0;
    height: 100%;
    width: 78px;
    background: #ff5757;
    padding: 6px 14px;
    z-index: 99;
    transition: all 0.5s ease;
  }
  .sidebar .open{
    width: 250px;
  }
  .sidebar .logo-details{
    height: 60px;
    display: flex;
    align-items: center;
    position: relative;
  }
  .sidebar .logo-details .icon{
    opacity: 0;
    transition: all 0.5s ease;
    height: 50px;
    width: 50px;
  }
  .sidebar .logo-details .logo_name{
    color: #fff;
    font-size: 20px;
    font-weight: 600;
    opacity: 0;
    transition: all 0.5s ease;
  }
  .sidebar.open .logo-details .icon,
  .sidebar.open .logo-details .logo_name{
    opacity: 1;
  }
  .sidebar .logo-details #btn{
    position: absolute;
    top: 50%;
    right: 0;
    transform: translateY(-50%);
    font-size: 23px;
    text-align: center;
    cursor: pointer;
    transition: all 0.5s ease;
  }
  .sidebar.open .logo-details #btn{
    text-align: right;
  }
  .sidebar i{
    color: #fff;
    height: 60px;
    min-width: 50px;
    font-size: 28px;
    text-align: center;
    line-height: 60px;
  }
  .sidebar .nav-list{
    margin-top: 20px;
    height: 100%;
  }
  .sidebar li{
    position: relative;
    margin: 8px 0;
    list-style: none;
  }
  .sidebar li .tooltip{
    position: absolute;
    top: -20px;
    left: calc(100% + 15px);
    z-index: 3;
    background: #fff;
    box-shadow: 0 5px 10px rgba(0, 0, 0, 0.3);
    padding: 6px 12px;
    border-radius: 4px;
    font-size: 15px;
    font-weight: 400;
    opacity: 0;
    white-space: nowrap;
    pointer-events: none;
    transition: 0s;
  }
  .sidebar li:hover .tooltip{
    opacity: 1;
    pointer-events: auto;
    transition: all 0.4s ease;
    top: 50%;
    transform: translateY(-50%);
  }
  .sidebar.open li .tooltip{
    display: none;
  }
  .sidebar input{
    font-size: 15px;
    color: #FFF;
    font-weight: 400;
    outline: none;
    height: 50px;
    width: 50px;
    border: none;
    border-radius: 12px;
    transition: all 0.5s ease;
    background: #1d1b31;
  }
  .sidebar.open input{
    padding: 0 20px 0 50px;
    width: 100%;
  }
  .sidebar li a{
    display: flex;
    height: 100%;
    width: 100%;
    border-radius: 12px;
    align-items: center;
    text-decoration: none;
    transition: all 0.4s ease;
    background: #ff5757;
  }
  .sidebar li a:hover{
    background: #FFF;
  }
  .sidebar li a .links_name{
    color: #fff;
    font-size: 15px;
    font-weight: 400;
    white-space: nowrap;
    opacity: 0;
    pointer-events: none;
    transition: 0.4s;
  }
  .sidebar.open li a .links_name{
    opacity: 1;
    pointer-events: auto;
  }
  .sidebar li i{
    height: 50px;
    line-height: 50px;
    font-size: 18px;
    border-radius: 12px;
  }
  .sidebar li a:hover .links_name,
  .sidebar li a:hover i{
    transition: all 0.5s ease;
    color: #11101D;
  }
  .sidebar li.profile{
    position: fixed;
    height: 60px;
    width: 78px;
    left: 0;
    bottom: -8px;
    padding: 10px 14px;
    background: #ff5757;
    transition: all 0.5s ease;
    overflow: hidden;
  }
  .sidebar.open li.profile{
    width: 250px;
  }
  .sidebar li .profile-details{
    display: flex;
    align-items: center;
    flex-wrap: nowrap;
  }
  .sidebar li img{
    height: 45px;
    width: 45px;
    object-fit: cover;
    border-radius: 6px;
    margin-right: 10px;
  }
  .sidebar li.profile .name,
  .sidebar li.profile .job{
    font-size: 15px;
    font-weight: 400;
    color: #fff;
    white-space: nowrap;
  }
  .sidebar li.profile .job{
    font-size: 12px;
  }
  .sidebar .profile #log_out{
    position: absolute;
    top: 50%;
    right: 0;
    transform: translateY(-50%);
    background: #ff5757;
    width: 100%;
    height: 60px;
    line-height: 60px;
    border-radius: 0;
    transition: all 0.5s ease;
  }
  .sidebar.open .profile #log_out{
    width: 50px;
    background: none;
  }
  @media (max-width: 420px) {
    .sidebar li .tooltip{
      display: none;
    }
  }
  .sidebar.open  ~ .content{
    left: 250px;
    width: calc(100% - 250px);
  }
</style>
