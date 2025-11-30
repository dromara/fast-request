import { defineClientConfig } from "vuepress/client";

import { ColorIcon } from "./components/ColorIcon.js";
import CarouselComponent from "./components/MyCarousel.vue";
import BgMusic from "./components/BgMusic.vue";
import { onMounted, onUnmounted } from "vue";


export default defineClientConfig({
  enhance: ({ app }) => {
    app.component("ColorIcon", ColorIcon);
    app.component("MyCarousel", CarouselComponent);
    app.component("BgMusic",BgMusic)
  },
    setup() {
        let clickHandler: ((e: MouseEvent) => void) | null = null;
        let hoverHandler: ((e: MouseEvent) => void) | null = null;

        onMounted(() => {
            if (typeof window === "undefined") return;

            // 点击：哪些元素算“按钮”
            const BUTTON_SELECTOR =
                "button," +
                "a," +
                "a.button," +
                "[role='button']," +
                ".vp-button," +
                ".vp-nav-link," +
                ".vp-sidebar-link";

            // === 音频对象 ===
            const clickAudio = new Audio("/sound/click.ogg");
            clickAudio.preload = "auto";

            // 只给 vp-site-info-navigator 用的 hover 音效
            const navHoverAudio = new Audio("/sound/focus.ogg");
            navHoverAudio.preload = "auto";

            // 记录上一次 hover 的导航元素，避免重复触发
            let lastHoverNavigator: HTMLElement | null = null;

            // === 点击按钮音效 ===
            clickHandler = (event: MouseEvent) => {
                const target = event.target as HTMLElement | null;
                if (!target) return;

                const button = target.closest(BUTTON_SELECTOR);
                if (!button) return;

                try {
                    clickAudio.currentTime = 0;
                    clickAudio.play();
                } catch (err) {
                    console.warn("点击音效播放失败：", err);
                }
            };

            // === 只给 .vp-site-info-navigator 的 hover 音效 ===
            hoverHandler = (event: MouseEvent) => {
                const target = event.target as HTMLElement | null;
                if (!target) return;

                const navigator = target.closest([".vp-site-info-navigator",
                        ".vp-feature-item"
                    ]
                ) as HTMLElement | null;

                if (!navigator) return;

                if (lastHoverNavigator === navigator) return;
                lastHoverNavigator = navigator;

                try {
                    navHoverAudio.currentTime = 0;
                    navHoverAudio.play();
                } catch (err) {
                    console.warn("导航 hover 音效播放失败：", err);
                }
            };

            window.addEventListener("click", clickHandler, { passive: true });
            window.addEventListener("mouseover", hoverHandler, { passive: true });
        });

        onUnmounted(() => {
            if (clickHandler) {
                window.removeEventListener("click", clickHandler);
            }
            if (hoverHandler) {
                window.removeEventListener("mouseover", hoverHandler);
            }
        });
    }

});
