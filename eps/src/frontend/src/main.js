import { createApp } from 'vue';
import App from './App.vue';
import router from "./router";
import store from "./store";
import "bootstrap";
import "bootstrap/dist/css/bootstrap.min.css";
import { FontAwesomeIcon } from './plugins/font-awesome';
import { Bootstrap4Pagination } from 'laravel-vue-pagination';
import { Bootstrap5Pagination } from 'laravel-vue-pagination';
import { TailwindPagination } from 'laravel-vue-pagination';

createApp(App)
    .use(router)
    .use(store)
    .use(Bootstrap4Pagination)
    .use(Bootstrap5Pagination)
    .use(TailwindPagination)
    .component("font-awesome-icon", FontAwesomeIcon)
    .mount("#app");
