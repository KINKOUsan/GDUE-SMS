import { createRouter, createWebHistory } from "vue-router";

import LayoutView from "@/views/layout/index.vue";
import MajorView from "@/views/major/index.vue";
import StudentView from "@/views/student/index.vue";
import IndexView from "@/views/index/index.vue";

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: "/",
            name: "",
            component: LayoutView,
            redirect: "/index",
            children: [
                {
                    path: "index",
                    name: "index",
                    component: IndexView,
                },
                {
                    path: "major",
                    name: "major",
                    component: MajorView,
                },
                {
                    path: "student",
                    name: "student",
                    component: StudentView,
                },
            ]
        }
    ]
});

export default router;