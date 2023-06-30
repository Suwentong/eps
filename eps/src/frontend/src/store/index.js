import { createStore } from "vuex";
import { auth } from "./auth.module";
import { dailyrep } from "./daily-report.module";

const store = createStore({
  modules: {
    auth,
    dailyrep,
  },
});

export default store;