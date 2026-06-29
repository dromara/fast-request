function ABDetected() {
  var adBlockDetected_div = document.createElement("div");
  document.body.appendChild(adBlockDetected_div);
  var navbar = document.querySelector(".vp-navbar");
  navbar.style.cssText = "transition:top 300ms;top:33px";
  adBlockDetected_div.style.cssText =
    "position: fixed; top: 0; left: 0; width: 100%; background: #E01E5A; color: #fff; z-index: 9999999999; font-size: 14px; text-align: center; line-height: 1.5; font-weight: bold; padding-top: 6px; padding-bottom: 6px;";
  adBlockDetected_div.innerHTML =
    "我们的广告服务商 <a style='color:#fff;text-decoration:underline' target='_blank' href=' '>并不跟踪您的隐私</a >，为了支持本站的长期运营，请将我们的网站 <a style='color: #fff;text-decoration:underline' target='_blank' href='https://wwads.cn/page/whitelist-wwads'>加入广告拦截器的白名单</a >。";
  document.getElementsByTagName("body")[0].appendChild(adBlockDetected_div);
  // add a close button to the right side of the div
  var adBlockDetected_close = document.createElement("div");
  adBlockDetected_close.style.cssText =
    "position: absolute; top: 0; right: 10px; width: 30px; height: 30px; background: #E01E5A; color: #fff; z-index: 9999999999; line-height: 30px; cursor: pointer;";
  adBlockDetected_close.innerHTML = "×";
  adBlockDetected_div.appendChild(adBlockDetected_close);
  // add a click event to the close button
  adBlockDetected_close.onclick = function () {
    this.parentNode.parentNode.removeChild(this.parentNode);
    navbar.style.cssText = "transition:top 300ms;top:0";
  };
}
