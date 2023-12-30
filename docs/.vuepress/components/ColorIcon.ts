import { type FunctionalComponent, h } from "vue";

export const ColorIcon: FunctionalComponent<{ icon: string }> = ({ icon }) =>
  h(
    "svg",
    { class: "icon svg-icon", "aria-hidden": "true" },
    h("use", { "xlink:href": `#icon-${icon}` })
  );
