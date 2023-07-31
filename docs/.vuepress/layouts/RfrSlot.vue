<script setup lang="ts">
import CommonWrapper from "@theme-hope/components/CommonWrapper";
import NormalPage from "@theme-hope/components/NormalPage";
import SkipLink from "@theme-hope/components/SkipLink";
import FadeSlideY from "@theme-hope/components/transitions/FadeSlideY";


let sidebarTopArray = [
  `<a href="https://jetbrains.com" target="_blank">
    <img className="no-zoom" height="50px" width="220px" src="/img/IntelliJ_IDEA.svg">
  </a>`,
]

setTimeout(() => {
        function shuffle(arr){
          var l = arr.length
          var index, temp
          while(l>0){
            index = Math.floor(Math.random()*l)
            temp = arr[l-1]
            arr[l-1] = arr[index]
            arr[index] = temp
            l--
          }
          return arr
        }

        const sidebarTop = globalThis.document.querySelector('.sidebar-slot-top')
        if (!sidebarTop) return
        sidebarTopArray = shuffle(sidebarTopArray);
        let _html = `<div style="width:230px;margin:0 auto">
            ${sidebarTopArray.slice(0, 4).join("")}
            <br/>
            <span style='color: gray;font-size: 11px;'>广告采用随机方式显示</span>
            <span style='float: right;'>️<a href='/fast-request/guide/sponsor.html' style='color: var(--theme-color);font-size: 11px;font-weight: bolder;'>成为赞助商</a></span>
            </div>`

        sidebarTop.innerHTML = _html

      }, 200);
</script>

<template>
  <SkipLink />
  <CommonWrapper>
    <FadeSlideY>
      <NormalPage>
<!--
        <template #tocBefore>
          <div class="slot-demo-block toc-slot-before"></div>
        </template>
-->
      </NormalPage>
    </FadeSlideY>


    <template #sidebarTop>
      <div class="slot-demo-block sidebar-slot-top" ></div>
    </template>
  </CommonWrapper>
</template>

<style lang="scss" scoped>
.slot-demo-inline {
  display: inline-block;
  background-color: var(--bg-color-tertiary);
  border-radius: 0.5rem;

  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 18px;
  width: 2rem;
  height: 2rem;
  margin: 0.25em;
}

.slot-demo-block {
  background-color: var(--bg-color-tertiary);
  border-radius: 0.5rem;
  padding: 1rem 2rem;
  margin: 0.5rem 1rem;

  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 18px;
  color: var(--text-color-light);
}
</style>

