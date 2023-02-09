import { createRouter, createWebHashHistory } from 'vue-router'

const routes = [
  {
    path: '/',
    redirect: '/FirstPage'
  },
  {
    path: '/FirstPage',
    name: 'FirstPage',
    component: () => import(/* webpackChunkName: "about" */ '../views/FirstPage.vue')
  },
  {
    path: '/SandBox',
    name: 'SandBox',
    component: () => import(/* webpackChunkName: "about" */ '../views/SandBox.vue')
  },
  {
    path: '/tips',
    name: 'tips',
    component: function () {
      return import('../views/tips.vue')
    }
  },
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
