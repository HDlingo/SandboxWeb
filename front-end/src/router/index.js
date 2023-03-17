import { createRouter, createWebHashHistory } from 'vue-router'

const routes = [
  {
    path: '/',
    // redirect: '/FirstPage'
    redirect: '/postTest'
  },
  {
    path: '/postTest',
    name: 'postTest',
    component: () => import(/* webpackChunkName: "about" */ '../views/postTest.vue')
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
  {
    path: '/choose',
    name: 'choose',
    component: function () {
      return import('../views/choose.vue')
    }
  },
  {
    path: '/course',
    name: 'course',
    component: function () {
      return import('../views/course.vue')
    }
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
