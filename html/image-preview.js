function initImagePreview(carousel) {
  const trigger = document.querySelector(".image-container");
  let isPreviewOpen = false;

  trigger.addEventListener("click", () => {
    if (isPreviewOpen || window.parent === window) return;

    isPreviewOpen = true;
    carousel.stopAutoPlay();
    window.parent.postMessage(
      {
        type: "home-advantage:preview",
        index: carousel.currentIndex,
        title: carousel.items[carousel.currentIndex]
          .querySelector(".title")
          .textContent.trim(),
      },
      window.location.origin,
    );
  });

  window.addEventListener("message", (event) => {
    if (
      event.origin !== window.location.origin ||
      event.source !== window.parent ||
      event.data?.type !== "home-advantage:close" ||
      !isPreviewOpen
    ) {
      return;
    }

    isPreviewOpen = false;
    trigger.focus({ preventScroll: true });
    if (carousel.isAutoPlaying) {
      carousel.timer = setTimeout(() => carousel.autoPlay(), carousel.interval);
    }
  });
}
