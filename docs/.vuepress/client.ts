import { defineClientConfig } from "@vuepress/client";
import { ColorIcon } from "./components/ColorIcon.js";

export default defineClientConfig({
  enhance: ({ app }) => {
    app.component("ColorIcon", ColorIcon);
  },
});
