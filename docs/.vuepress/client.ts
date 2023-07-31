import { defineClientConfig } from "@vuepress/client";
import { ColorIcon } from "./components/ColorIcon.js";
import RfrSlot from "./layouts/RfrSlot.vue";

export default defineClientConfig({
  enhance: ({ app }) => {
    app.component("ColorIcon", ColorIcon);
  },
  layouts: { RfrSlot},
});
