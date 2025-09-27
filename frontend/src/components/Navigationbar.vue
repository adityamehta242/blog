<template>
  <nav>
    <h3>Blog Platform</h3>
    <div>
      <RouterLink to="/">Home</RouterLink>
      <RouterLink to="/draft">Draft</RouterLink>
      <RouterLink to="/categories">Category</RouterLink>
      <RouterLink to="/tags">Tags</RouterLink>
    </div>

    <button aria-label="User Profile" @click="toggleDropdown">
      <img src="/profile-user.png" alt="User Profile" />
    </button>

    <!-- Dropdown menu -->
    <div v-if="dropdownOpen" class="dropdown-menu" @click.outside="closeDropdown">
      <ul>
        <li @click="goToMyPost">MyPost</li>
        <li @click="logout">Logout</li>
      </ul>
    </div>
  </nav>
</template>

<script lang="ts" setup>
import { ref } from 'vue';
import { RouterLink, useRouter } from 'vue-router';

const dropdownOpen = ref(false);
const router = useRouter();

function toggleDropdown() {
  dropdownOpen.value = !dropdownOpen.value;
}

function closeDropdown() {
  dropdownOpen.value = false;
}

function goToMyPost() {
  dropdownOpen.value = false;
  console.log('Navigating to MyPost page');
  router.push('/mypost');
}

function logout() {
  dropdownOpen.value = false;
  console.log('Performing logout');
  //TODO: Perform logout logic here, e.g., clear auth tokens, redirect to login
}
</script>

<style scoped>
* {
    background-color: #37353E;
}

nav {
  position: relative;
  display: flex;
  align-items: center;
  justify-content: space-evenly;
  height: 4.5rem;
  width: 100%;
  color: white;
}
nav div {
  display: flex;
  align-items: center;
}
button {
  width: 3rem;
  height: 3rem;
  border-radius: 50%;
  display: flex;
  justify-content: center;
  align-items: center;
  border: none;
  background: transparent;
  cursor: pointer;
  position: relative;
}

button img {
  width: 3rem;
  height: 3rem;
  border-radius: 50%;
  object-fit: cover;
}

.dropdown-menu {
  position: absolute;
  top: 4.5rem; 
  right: 1rem;
  background: white;
  border: 1px solid #ccc;
  border-radius: 5px;
  width: 120px;
  box-shadow: 0 2px 8px rgba(0,0,0,0.1);
}

.dropdown-menu ul {
  list-style: none;
  margin: 0;
  padding: 0;
}

.dropdown-menu li {
  padding: 0.75rem 1rem;
  cursor: pointer;
}

.dropdown-menu li:hover {
  background-color: #eee;
}
</style>
