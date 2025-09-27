import { createRouter, createWebHistory, type RouteRecordRaw } from "vue-router";

const routes : RouteRecordRaw[] = [
    {path: "/" , component : () => import("../components/Home.vue")},
    {path: "/draft" , component : () => import("../components/Draft.vue")},
    {path: "/categories" , component : () => import("../components/Category.vue")},
    {path : "/tags" , component : () => import('../components/Tag.vue')},
    {path : "/user" , component : () => import('../components/User.vue')},
    {path : "/mypost" , component : () => import('../components/MyPost.vue')},
    {path : "/signin" , component : () => import("../components/SignIn.vue")},
    {path : "/signup" , component : () => import("../components/SignUp.vue")},
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router;