var Fe=Object.getPrototypeOf;var Ne=Reflect.get;var dt=(e,t,s)=>Ne(Fe(e),s,t);import{C as Zt,u as y,m as Yt,a as T,e as S,a3 as Xt,F as Jt,az as Re,c as _,p as gt,t as te,o as E,aD as Ve,y as z,Y as Ee,aE as We,aF as vt,w as X,aG as Ke,aH as ee,aI as He,z as se,aJ as x,aK as _t,aL as ne,s as Q,v as ae,aM as Qe,aN as ie,X as oe,aO as J,aP as qe,au as ze,ae as B,W as Ue,aQ as je,$ as Ze,aR as Ye,aS as xt,aT as Xe,af as wt,aU as Je,aV as ts,E as es,H as Tt,l as Y,a2 as ss,aW as ns,aX as as,aY as is,_ as le,d as kt}from"./vidstack-CSaHpIQV-nANFaKGI.js";import{i as re}from"./app-VAKH5ZcI.js";import{e as os,c as ls,t as Kt,A as rs,l as St,D as Ht,x as i,o as us,n as st,L as Ct,i as cs,a as ue}from"./vidstack-rsZGrNIW-cbZ7VXwC.js";const ce=Xt();function b(){return Jt(ce)}const ds={clickToPlay:!0,clickToFullscreen:!0,controls:["play-large","play","progress","current-time","mute+volume","captions","settings","pip","airplay","fullscreen"],customIcons:!1,displayDuration:!1,download:null,markers:null,invertTime:!0,thumbnails:null,toggleTime:!0,translations:null,seekTime:10,speed:[.5,.75,1,1.25,1.5,1.75,2,4]},Nt=class Nt extends Zt{onSetup(){this.a=y(),Yt(ce,{...this.$props,previewTime:T(0)})}};Nt.props=ds;let ft=Nt;function ps(e,t){const{canAirPlay:s,canFullscreen:n,canPictureInPicture:a,controlsHidden:l,currentTime:r,fullscreen:c,hasCaptions:p,isAirPlayConnected:v,paused:f,pictureInPicture:m,playing:$,pointer:g,poster:w,textTrack:A,viewType:k,waiting:L}=t.$state;e.classList.add("plyr"),e.classList.add("plyr--full-ui");const G={"plyr--airplay-active":v,"plyr--airplay-supported":s,"plyr--fullscreen-active":c,"plyr--fullscreen-enabled":n,"plyr--hide-controls":l,"plyr--is-touch":()=>g()==="coarse","plyr--loading":L,"plyr--paused":f,"plyr--pip-active":m,"plyr--pip-enabled":a,"plyr--playing":$,"plyr__poster-enabled":w,"plyr--stopped":()=>f()&&r()===0,"plyr--captions-active":A,"plyr--captions-enabled":p},R=Re();for(const M of Object.keys(G))R.add(S(()=>void e.classList.toggle(M,!!G[M]())));return R.add(S(()=>{const M=`plyr--${k()}`;return e.classList.add(M),()=>e.classList.remove(M)}),S(()=>{var V;const{$provider:M}=t,K=(V=M())==null?void 0:V.type,H=`plyr--${ms(K)?"html5":K}`;return e.classList.toggle(H,!!K),()=>e.classList.remove(H)})),()=>R.empty()}function ms(e){return e==="audio"||e==="video"}class vs extends ls{constructor(t){super(t),this.h=null,this.w=!1,this.$=null,this.w=t.type===Kt.ATTRIBUTE||t.type===Kt.BOOLEAN_ATTRIBUTE}render(t){return t!==this.h&&(this.disconnected(),this.h=t,this.isConnected&&this.Gl()),this.h?this.x(gt(this.h)):rs}reconnected(){this.Gl()}disconnected(){var t;(t=this.$)==null||t.call(this),this.$=null}Gl(){this.h&&(this.$=S(this.l.bind(this)))}x(t){return this.w?St(t):t}y(t){this.setValue(this.x(t))}l(){var t;this.y((t=this.h)==null?void 0:t.call(this))}}function o(e){return os(vs)(_(e))}class de{constructor(t,s){this._m=t,this.La=s,this.elements=new Set,this.Gc=te(this.Ha.bind(this))}connect(){this.Ha();const t=new MutationObserver(this.Gc);for(const s of this._m)t.observe(s,{childList:!0,subtree:!0});E(()=>t.disconnect()),E(this.disconnect.bind(this))}disconnect(){this.elements.clear()}assign(t,s){Ve(t)?(s.textContent="",s.append(t)):(Ht(null,s),Ht(t,s)),s.style.display||(s.style.display="contents");const n=s.firstElementChild;if(!n)return;const a=s.getAttribute("data-class");a&&n.classList.add(...a.split(" "))}Ha(t){if(t&&!t.some(a=>a.addedNodes.length))return;let s=!1,n=this._m.flatMap(a=>[...a.querySelectorAll("slot")]);for(const a of n)!a.hasAttribute("name")||this.elements.has(a)||(this.elements.add(a),s=!0);s&&this.La(this.elements)}}let fs=0,Z="data-slot-id";class Dt{constructor(t){this._m=t,this.Gc=te(this.Ha.bind(this)),this.slots=new de(t,this.Ha.bind(this))}connect(){this.slots.connect(),this.Ha();const t=new MutationObserver(this.Gc);for(const s of this._m)t.observe(s,{childList:!0});E(()=>t.disconnect())}Ha(){for(const t of this._m)for(const s of t.children){if(s.nodeType!==1)continue;const n=s.getAttribute("slot");if(!n)continue;s.style.display="none";let a=s.getAttribute(Z);a||s.setAttribute(Z,a=++fs+"");for(const l of this.slots.elements){if(l.getAttribute("name")!==n||l.getAttribute(Z)===a)continue;const r=document.importNode(s,!0);n.includes("-icon")&&r.classList.add("vds-icon"),r.style.display="",r.removeAttribute("slot"),this.slots.assign(r,l),l.setAttribute(Z,a)}}}}function pe({name:e,class:t,state:s,paths:n,viewBox:a="0 0 32 32"}){return i`<svg
    class="${"vds-icon"+(t?` ${t}`:"")}"
    viewBox="${a}"
    fill="none"
    aria-hidden="true"
    focusable="false"
    xmlns="http://www.w3.org/2000/svg"
    data-icon=${St(e??s)}
  >
    ${z(n)?us(n):o(n)}
  </svg>`}class $s{constructor(t){this._m=t,this.dn={},this.gn=!1,this.slots=new de(t,this.hn.bind(this))}connect(){this.slots.connect()}load(){this.Pf().then(t=>{this.dn=t,this.gn=!0,this.hn()})}*jn(){for(const t of Object.keys(this.dn)){const s=`${t}-icon`;for(const n of this.slots.elements)n.name===s&&(yield{icon:this.dn[t],slot:n})}}hn(){if(this.gn)for(const{icon:t,slot:s}of this.jn())this.slots.assign(t,s)}}class me extends $s{connect(){super.connect();const{player:t}=y();if(!t.el)return;let s,n=new IntersectionObserver(a=>{var l;(l=a[0])!=null&&l.isIntersecting&&(s==null||s(),s=void 0,this.load())});n.observe(t.el),s=E(()=>n.disconnect())}}function pt(e){const{style:t}=new Option;return t.color=e,t.color.match(/\((.*?)\)/)[1].replace(/,/g," ")}let Qt=!1,mt=new Set;function bs(){const{player:e}=y();mt.add(e),E(()=>mt.delete(e)),Qt||(Ee(()=>{for(const t of We(vt)){const s=vt[t],n=Ke[t],a=`--media-user-${X(t)}`,l=`vds-player:${X(t)}`;S(()=>{var v;const r=s(),c=r===n,p=c?null:hs(e,t,r);for(const f of mt)(v=f.el)==null||v.style.setProperty(a,p);c?localStorage.removeItem(l):localStorage.setItem(l,r)})}},null),Qt=!0)}function hs(e,t,s){var n;switch(t){case"fontFamily":const a=s==="capitals"?"small-caps":"";return(n=e.el)==null||n.style.setProperty("--media-user-font-variant",a),gs(s);case"fontSize":case"textOpacity":case"textBgOpacity":case"displayBgOpacity":return ys(s);case"textColor":return`rgb(${pt(s)} / var(--media-user-text-opacity, 1))`;case"textShadow":return _s(s);case"textBg":return`rgb(${pt(s)} / var(--media-user-text-bg-opacity, 1))`;case"displayBg":return`rgb(${pt(s)} / var(--media-user-display-bg-opacity, 1))`}}function ys(e){return(parseInt(e)/100).toString()}function gs(e){switch(e){case"mono-serif":return'"Courier New", Courier, "Nimbus Mono L", "Cutive Mono", monospace';case"mono-sans":return'"Deja Vu Sans Mono", "Lucida Console", Monaco, Consolas, "PT Mono", monospace';case"pro-sans":return'Roboto, "Arial Unicode Ms", Arial, Helvetica, Verdana, "PT Sans Caption", sans-serif';case"casual":return'"Comic Sans MS", Impact, Handlee, fantasy';case"cursive":return'"Monotype Corsiva", "URW Chancery L", "Apple Chancery", "Dancing Script", cursive';case"capitals":return'"Arial Unicode Ms", Arial, Helvetica, Verdana, "Marcellus SC", sans-serif + font-variant=small-caps';default:return'"Times New Roman", Times, Georgia, Cambria, "PT Serif Caption", serif'}}function _s(e){switch(e){case"drop shadow":return"rgb(34, 34, 34) 1.86389px 1.86389px 2.79583px, rgb(34, 34, 34) 1.86389px 1.86389px 3.72778px, rgb(34, 34, 34) 1.86389px 1.86389px 4.65972px";case"raised":return"rgb(34, 34, 34) 1px 1px, rgb(34, 34, 34) 2px 2px";case"depressed":return"rgb(204, 204, 204) 1px 1px, rgb(34, 34, 34) -1px -1px";case"outline":return"rgb(34, 34, 34) 0px 0px 1.86389px, rgb(34, 34, 34) 0px 0px 1.86389px, rgb(34, 34, 34) 0px 0px 1.86389px, rgb(34, 34, 34) 0px 0px 1.86389px, rgb(34, 34, 34) 0px 0px 1.86389px";default:return""}}const ve=Xt();function u(){return Jt(ve)}const xs={colorScheme:"system",download:null,customIcons:!1,disableTimeSlider:!1,menuContainer:null,menuGroup:"bottom",noAudioGain:!1,noGestures:!1,noKeyboardAnimations:!1,noModal:!1,noScrubGesture:!1,playbackRates:{min:0,max:2,step:.25},audioGains:{min:0,max:300,step:25},seekStep:10,sliderChaptersMinWidth:325,hideQualityBitrate:!1,smallWhen:!1,thumbnails:null,translations:null,when:!1};var ws=Object.defineProperty,Ts=Object.getOwnPropertyDescriptor,fe=(e,t,s,n)=>{for(var a=Ts(t,s),l=e.length-1,r;l>=0;l--)(r=e[l])&&(a=r(t,s,a)||a);return a&&ws(t,s,a),a};const Rt=class Rt extends Zt{constructor(){super(...arguments),this.cn=_(()=>{const t=this.$props.when();return this.fn(t)}),this.$m=_(()=>{const t=this.$props.smallWhen();return this.fn(t)})}get isMatch(){return this.cn()}get isSmallLayout(){return this.$m()}onSetup(){this.a=y(),this.setAttributes({"data-match":this.cn,"data-sm":()=>this.$m()?"":null,"data-lg":()=>this.$m()?null:"","data-size":()=>this.$m()?"sm":"lg","data-no-scrub-gesture":this.$props.noScrubGesture}),Yt(ve,{...this.$props,when:this.cn,smallWhen:this.$m,userPrefersAnnouncements:T(!0),userPrefersKeyboardAnimations:T(!0),menuPortal:T(null)})}onAttach(t){ee(t,this.$props.colorScheme)}fn(t){return t!=="never"&&(He(t)?t:_(()=>t(this.a.player.state))())}};Rt.props=xs;let W=Rt;fe([se],W.prototype,"isMatch");fe([se],W.prototype,"isSmallLayout");function $e(e,t){S(()=>{const{player:s}=y(),n=s.el;return n&&Q(n,"data-layout",t()&&e),()=>n==null?void 0:n.removeAttribute("data-layout")})}function C(e,t){var s;return((s=e())==null?void 0:s[t])??t}function Pt(){return o(()=>{const{translations:e,userPrefersAnnouncements:t}=u();return t()?i`<media-announcer .translations=${o(e)}></media-announcer>`:null})}function D(e,t=""){return i`<slot
    name=${`${e}-icon`}
    data-class=${`vds-icon vds-${e}-icon${t?` ${t}`:""}`}
  ></slot>`}function U(e){return e.map(t=>D(t))}function d(e,t){return o(()=>C(e,t))}function At({tooltip:e}){const{translations:t}=u(),{remotePlaybackState:s}=x(),n=o(()=>{const l=C(t,"AirPlay"),r=ae(s());return`${l} ${r}`}),a=d(t,"AirPlay");return i`
    <media-tooltip class="vds-airplay-tooltip vds-tooltip">
      <media-tooltip-trigger>
        <media-airplay-button class="vds-airplay-button vds-button" aria-label=${n}>
          ${D("airplay")}
        </media-airplay-button>
      </media-tooltip-trigger>
      <media-tooltip-content class="vds-tooltip-content" placement=${e}>
        <span class="vds-airplay-tooltip-text">${a}</span>
      </media-tooltip-content>
    </media-tooltip>
  `}function be({tooltip:e}){const{translations:t}=u(),{remotePlaybackState:s}=x(),n=o(()=>{const l=C(t,"Google Cast"),r=ae(s());return`${l} ${r}`}),a=d(t,"Google Cast");return i`
    <media-tooltip class="vds-google-cast-tooltip vds-tooltip">
      <media-tooltip-trigger>
        <media-google-cast-button class="vds-google-cast-button vds-button" aria-label=${n}>
          ${D("google-cast")}
        </media-google-cast-button>
      </media-tooltip-trigger>
      <media-tooltip-content class="vds-tooltip-content" placement=${e}>
        <span class="vds-google-cast-tooltip-text">${a}</span>
      </media-tooltip-content>
    </media-tooltip>
  `}function nt({tooltip:e}){const{translations:t}=u(),s=d(t,"Play"),n=d(t,"Pause");return i`
    <media-tooltip class="vds-play-tooltip vds-tooltip">
      <media-tooltip-trigger>
        <media-play-button
          class="vds-play-button vds-button"
          aria-label=${d(t,"Play")}
        >
          ${U(["play","pause","replay"])}
        </media-play-button>
      </media-tooltip-trigger>
      <media-tooltip-content class="vds-tooltip-content" placement=${e}>
        <span class="vds-play-tooltip-text">${s}</span>
        <span class="vds-pause-tooltip-text">${n}</span>
      </media-tooltip-content>
    </media-tooltip>
  `}function qt({tooltip:e,ref:t=ze}){const{translations:s}=u(),n=d(s,"Mute"),a=d(s,"Unmute");return i`
    <media-tooltip class="vds-mute-tooltip vds-tooltip">
      <media-tooltip-trigger>
        <media-mute-button
          class="vds-mute-button vds-button"
          aria-label=${d(s,"Mute")}
          ${st(t)}
        >
          ${U(["mute","volume-low","volume-high"])}
        </media-mute-button>
      </media-tooltip-trigger>
      <media-tooltip-content class="vds-tooltip-content" placement=${e}>
        <span class="vds-mute-tooltip-text">${a}</span>
        <span class="vds-unmute-tooltip-text">${n}</span>
      </media-tooltip-content>
    </media-tooltip>
  `}function Mt({tooltip:e}){const{translations:t}=u(),s=d(t,"Closed-Captions On"),n=d(t,"Closed-Captions Off");return i`
    <media-tooltip class="vds-caption-tooltip vds-tooltip">
      <media-tooltip-trigger>
        <media-caption-button
          class="vds-caption-button vds-button"
          aria-label=${d(t,"Captions")}
        >
          ${U(["cc-on","cc-off"])}
        </media-caption-button>
      </media-tooltip-trigger>
      <media-tooltip-content class="vds-tooltip-content" placement=${e}>
        <span class="vds-cc-on-tooltip-text">${n}</span>
        <span class="vds-cc-off-tooltip-text">${s}</span>
      </media-tooltip-content>
    </media-tooltip>
  `}function ks(){const{translations:e}=u(),t=d(e,"Enter PiP"),s=d(e,"Exit PiP");return i`
    <media-tooltip class="vds-pip-tooltip vds-tooltip">
      <media-tooltip-trigger>
        <media-pip-button
          class="vds-pip-button vds-button"
          aria-label=${d(e,"PiP")}
        >
          ${U(["pip-enter","pip-exit"])}
        </media-pip-button>
      </media-tooltip-trigger>
      <media-tooltip-content class="vds-tooltip-content">
        <span class="vds-pip-enter-tooltip-text">${t}</span>
        <span class="vds-pip-exit-tooltip-text">${s}</span>
      </media-tooltip-content>
    </media-tooltip>
  `}function he({tooltip:e}){const{translations:t}=u(),s=d(t,"Enter Fullscreen"),n=d(t,"Exit Fullscreen");return i`
    <media-tooltip class="vds-fullscreen-tooltip vds-tooltip">
      <media-tooltip-trigger>
        <media-fullscreen-button
          class="vds-fullscreen-button vds-button"
          aria-label=${d(t,"Fullscreen")}
        >
          ${U(["fs-enter","fs-exit"])}
        </media-fullscreen-button>
      </media-tooltip-trigger>
      <media-tooltip-content class="vds-tooltip-content" placement=${e}>
        <span class="vds-fs-enter-tooltip-text">${s}</span>
        <span class="vds-fs-exit-tooltip-text">${n}</span>
      </media-tooltip-content>
    </media-tooltip>
  `}function zt({backward:e,tooltip:t}){const{translations:s,seekStep:n}=u(),a=e?"Seek Backward":"Seek Forward",l=d(s,a);return i`
    <media-tooltip class="vds-seek-tooltip vds-tooltip">
      <media-tooltip-trigger>
        <media-seek-button
          class="vds-seek-button vds-button"
          seconds=${o(()=>(e?-1:1)*n())}
          aria-label=${l}
        >
          ${D(e?"seek-backward":"seek-forward")}
        </media-seek-button>
      </media-tooltip-trigger>
      <media-tooltip-content class="vds-tooltip-content" placement=${t}>
        ${d(s,a)}
      </media-tooltip-content>
    </media-tooltip>
  `}function ye(){const{translations:e}=u(),{live:t}=x(),s=d(e,"Skip To Live"),n=d(e,"LIVE");return t()?i`
        <media-live-button class="vds-live-button" aria-label=${s}>
          <span class="vds-live-button-text">${n}</span>
        </media-live-button>
      `:null}function Ot(){return o(()=>{const{download:e,translations:t}=u(),s=e();if(Qe(s))return null;const{source:n,title:a}=x(),l=n(),r=ie({title:a(),src:l,download:s});return r?i`
          <media-tooltip class="vds-download-tooltip vds-tooltip">
            <media-tooltip-trigger>
              <a
                role="button"
                class="vds-download-button vds-button"
                aria-label=${d(t,"Download")}
                href=${r.url+`?download=${r.name}`}
                download=${r.name}
                target="_blank"
              >
                <slot name="download-icon" data-class="vds-icon" />
              </a>
            </media-tooltip-trigger>
            <media-tooltip-content class="vds-tooltip-content" placement="top">
              ${d(t,"Download")}
            </media-tooltip-content>
          </media-tooltip>
        `:null})}function It(){const{translations:e}=u();return i`
    <media-captions
      class="vds-captions"
      .exampleText=${d(e,"Captions look like this")}
    ></media-captions>
  `}function O(){return i`<div class="vds-controls-spacer"></div>`}function ge(e,t){return i`
    <media-menu-portal .container=${o(e)} disabled="fullscreen">
      ${t}
    </media-menu-portal>
  `}function _e(e,t,s){let n=z(e)?document.querySelector(e):e;n||(n=document.body);const a=document.createElement("div");a.style.display="contents",a.classList.add(t),n.append(a),S(()=>{if(!a)return;const{viewType:r}=x(),c=s();Q(a,"data-view-type",r()),Q(a,"data-sm",c),Q(a,"data-lg",!c),Q(a,"data-size",c?"sm":"lg")});const{colorScheme:l}=u();return ee(a,l),a}function xe({placement:e,tooltip:t,portal:s}){const{textTracks:n}=y(),{viewType:a,clipStartTime:l,clipEndTime:r}=x(),{translations:c,thumbnails:p,menuPortal:v,noModal:f,menuGroup:m,smallWhen:$}=u();if(_(()=>{var Et;const M=l(),K=r()||1/0,H=T(null);oe(n,"chapters",H.set);const V=(Et=H())==null?void 0:Et.cues.filter(Wt=>Wt.startTime<=K&&Wt.endTime>=M);return!(V!=null&&V.length)})())return null;const w=_(()=>f()?J(e):$()?null:J(e)),A=_(()=>!$()&&m()==="bottom"&&a()==="video"?26:0),k=T(!1);function L(){k.set(!0)}function G(){k.set(!1)}const R=i`
    <media-menu-items
      class="vds-chapters-menu-items vds-menu-items"
      placement=${o(w)}
      offset=${o(A)}
    >
      ${o(()=>k()?i`
          <media-chapters-radio-group
            class="vds-chapters-radio-group vds-radio-group"
            .thumbnails=${o(p)}
          >
            <template>
              <media-radio class="vds-chapter-radio vds-radio">
                <media-thumbnail class="vds-thumbnail"></media-thumbnail>
                <div class="vds-chapter-radio-content">
                  <span class="vds-chapter-radio-label" data-part="label"></span>
                  <span class="vds-chapter-radio-start-time" data-part="start-time"></span>
                  <span class="vds-chapter-radio-duration" data-part="duration"></span>
                </div>
              </media-radio>
            </template>
          </media-chapters-radio-group>
        `:null)}
    </media-menu-items>
  `;return i`
    <media-menu class="vds-chapters-menu vds-menu" @open=${L} @close=${G}>
      <media-tooltip class="vds-tooltip">
        <media-tooltip-trigger>
          <media-menu-button
            class="vds-menu-button vds-button"
            aria-label=${d(c,"Chapters")}
          >
            ${D("menu-chapters")}
          </media-menu-button>
        </media-tooltip-trigger>
        <media-tooltip-content
          class="vds-tooltip-content"
          placement=${_t(t)?o(t):t}
        >
          ${d(c,"Chapters")}
        </media-tooltip-content>
      </media-tooltip>
      ${s?ge(v,R):R}
    </media-menu>
  `}let Ss=0;function P({label:e="",value:t="",children:s}){if(!e)return i`
      <div class="vds-menu-section">
        <div class="vds-menu-section-body">${s}</div>
      </div>
    `;const n=`vds-menu-section-${++Ss}`;return i`
    <section class="vds-menu-section" role="group" aria-labelledby=${n}>
      <div class="vds-menu-section-title">
        <header id=${n}>${e}</header>
        ${t?i`<div class="vds-menu-section-value">${t}</div>`:null}
      </div>
      <div class="vds-menu-section-body">${s}</div>
    </section>
  `}function j({label:e,children:t}){return i`
    <div class="vds-menu-item">
      <div class="vds-menu-item-label">${e}</div>
      ${t}
    </div>
  `}function N({label:e,icon:t,hint:s}){return i`
    <media-menu-button class="vds-menu-item">
      ${D("menu-arrow-left","vds-menu-close-icon")}
      ${t?D(t,"vds-menu-item-icon"):null}
      <span class="vds-menu-item-label">${o(e)}</span>
      <span class="vds-menu-item-hint" data-part="hint">${s?o(s):null} </span>
      ${D("menu-arrow-right","vds-menu-open-icon")}
    </media-menu-button>
  `}function Cs({value:e=null,options:t,hideLabel:s=!1,children:n=null,onChange:a=null}){function l(r){const{value:c,label:p}=r;return i`
      <media-radio class="vds-radio" value=${c}>
        ${D("menu-radio-check")}
        ${s?null:i`
              <span class="vds-radio-label" data-part="label">
                ${z(p)?p:o(p)}
              </span>
            `}
        ${_t(n)?n(r):n}
      </media-radio>
    `}return i`
    <media-radio-group
      class="vds-radio-group"
      value=${z(e)?e:e?o(e):""}
      @change=${a}
    >
      ${B(t)?t.map(l):o(()=>t().map(l))}
    </media-radio-group>
  `}function Ds(e){return B(e)?e.map(t=>({label:t,value:t.toLowerCase()})):Object.keys(e).map(t=>({label:t,value:e[t]}))}function at(){return i`
    <div class="vds-slider-track"></div>
    <div class="vds-slider-track-fill vds-slider-track"></div>
    <div class="vds-slider-thumb"></div>
  `}function it(){return i`
    <media-slider-steps class="vds-slider-steps">
      <template>
        <div class="vds-slider-step"></div>
      </template>
    </media-slider-steps>
  `}function ot({label:e=null,value:t=null,upIcon:s="",downIcon:n="",children:a,isMin:l,isMax:r}){const c=e||t,p=[n?D(n,"down"):null,a,s?D(s,"up"):null];return i`
    <div
      class=${`vds-menu-item vds-menu-slider-item${c?" group":""}`}
      data-min=${o(()=>l()?"":null)}
      data-max=${o(()=>r()?"":null)}
    >
      ${c?i`
            <div class="vds-menu-slider-title">
              ${[e?i`<div>${e}</div>`:null,t?i`<div>${t}</div>`:null]}
            </div>
            <div class="vds-menu-slider-body">${p}</div>
          `:p}
    </div>
  `}const Ps={...Je,upIcon:"menu-opacity-up",downIcon:"menu-opacity-down"},Lt={...ts,upIcon:"menu-opacity-up",downIcon:"menu-opacity-down"};function As(){return o(()=>{const{hasCaptions:e}=x(),{translations:t}=u();return e()?i`
      <media-menu class="vds-font-menu vds-menu">
        ${N({label:()=>C(t,"Caption Styles")})}
        <media-menu-items class="vds-menu-items">
          ${[P({label:d(t,"Font"),children:[Ms(),Os()]}),P({label:d(t,"Text"),children:[Is(),Bs(),Ls()]}),P({label:d(t,"Text Background"),children:[Gs(),Fs()]}),P({label:d(t,"Display Background"),children:[Ns(),Rs()]}),P({children:[Vs()]})]}
        </media-menu-items>
      </media-menu>
    `:null})}function Ms(){return I({label:"Family",option:Ye,type:"fontFamily"})}function Os(){return I({label:"Size",option:Ps,type:"fontSize"})}function Is(){return I({label:"Color",option:xt,type:"textColor"})}function Ls(){return I({label:"Opacity",option:Lt,type:"textOpacity"})}function Bs(){return I({label:"Shadow",option:Xe,type:"textShadow"})}function Gs(){return I({label:"Color",option:xt,type:"textBg"})}function Fs(){return I({label:"Opacity",option:Lt,type:"textBgOpacity"})}function Ns(){return I({label:"Color",option:xt,type:"displayBg"})}function Rs(){return I({label:"Opacity",option:Lt,type:"displayBgOpacity"})}function Vs(){const{translations:e}=u();return i`
    <button class="vds-menu-item" role="menuitem" @click=${je}>
      <span class="vds-menu-item-label">${o(()=>C(e,"Reset"))}</span>
    </button>
  `}function I({label:e,option:t,type:s}){const{player:n}=y(),{translations:a}=u(),l=vt[s],r=()=>C(a,e);function c(){es(),n.dispatchEvent(new Event("vds-font-change"))}if(t.type==="color"){let f=function(m){l.set(m.target.value),c()};return j({label:o(r),children:i`
        <input
          class="vds-color-picker"
          type="color"
          .value=${o(l)}
          @input=${f}
        />
      `})}if(t.type==="slider"){let f=function(k){l.set(k.detail+"%"),c()};const{min:m,max:$,step:g,upIcon:w,downIcon:A}=t;return ot({label:o(r),value:o(l),upIcon:w,downIcon:A,isMin:()=>l()===m+"%",isMax:()=>l()===$+"%",children:i`
        <media-slider
          class="vds-slider"
          min=${m}
          max=${$}
          step=${g}
          key-step=${g}
          .value=${o(()=>parseInt(l()))}
          aria-label=${o(r)}
          @value-change=${f}
          @drag-value-change=${f}
        >
          ${at()}${it()}
        </media-slider>
      `})}const p=Ds(t.values),v=()=>{var $;const f=l(),m=(($=p.find(g=>g.value===f))==null?void 0:$.label)||"";return C(a,z(m)?m:m())};return i`
    <media-menu class=${`vds-${X(s)}-menu vds-menu`}>
      ${N({label:r,hint:v})}
      <media-menu-items class="vds-menu-items">
        ${Cs({value:l,options:p,onChange({detail:f}){l.set(f),c()}})}
      </media-menu-items>
    </media-menu>
  `}function lt({label:e,checked:t,defaultChecked:s=!1,storageKey:n,onChange:a}){const{translations:l}=u(),r=n?localStorage.getItem(n):null,c=T(!!(r??s)),p=T(!1),v=o(Ze(c)),f=d(l,e);n&&a(gt(c)),t&&S(()=>void c.set(t()));function m(w){(w==null?void 0:w.button)!==1&&(c.set(A=>!A),n&&localStorage.setItem(n,c()?"1":""),a(c(),w),p.set(!1))}function $(w){wt(w)&&m()}function g(w){w.button===0&&p.set(!0)}return i`
    <div
      class="vds-menu-checkbox"
      role="menuitemcheckbox"
      tabindex="0"
      aria-label=${f}
      aria-checked=${v}
      data-active=${o(()=>p()?"":null)}
      @pointerup=${m}
      @pointerdown=${g}
      @keydown=${$}
    ></div>
  `}function Es(){return o(()=>{const{translations:e}=u();return i`
      <media-menu class="vds-accessibility-menu vds-menu">
        ${N({label:()=>C(e,"Accessibility"),icon:"menu-accessibility"})}
        <media-menu-items class="vds-menu-items">
          ${[P({children:[Ws(),Ks()]}),P({children:[As()]})]}
        </media-menu-items>
      </media-menu>
    `})}function Ws(){const{userPrefersAnnouncements:e,translations:t}=u(),s="Announcements";return j({label:d(t,s),children:lt({label:s,storageKey:"vds-player::announcements",onChange(n){e.set(n)}})})}function Ks(){return o(()=>{const{translations:e,userPrefersKeyboardAnimations:t,noKeyboardAnimations:s}=u(),{viewType:n}=x();if(_(()=>n()!=="video"||s())())return null;const l="Keyboard Animations";return j({label:d(e,l),children:lt({label:l,defaultChecked:!0,storageKey:"vds-player::keyboard-animations",onChange(r){t.set(r)}})})})}function Hs(){return o(()=>{const{noAudioGain:e,translations:t}=u(),{audioTracks:s,canSetAudioGain:n}=x();return _(()=>!(n()&&!e())&&s().length<=1)()?null:i`
      <media-menu class="vds-audio-menu vds-menu">
        ${N({label:()=>C(t,"Audio"),icon:"menu-audio"})}
        <media-menu-items class="vds-menu-items">
          ${[Qs(),qs()]}
        </media-menu-items>
      </media-menu>
    `})}function Qs(){return o(()=>{const{translations:e}=u(),{audioTracks:t}=x(),s=d(e,"Default");return _(()=>t().length<=1)()?null:P({children:i`
        <media-menu class="vds-audio-tracks-menu vds-menu">
          ${N({label:()=>C(e,"Track")})}
          <media-menu-items class="vds-menu-items">
            <media-audio-radio-group
              class="vds-audio-track-radio-group vds-radio-group"
              empty-label=${s}
            >
              <template>
                <media-radio class="vds-audio-track-radio vds-radio">
                  <slot name="menu-radio-check-icon" data-class="vds-icon"></slot>
                  <span class="vds-radio-label" data-part="label"></span>
                </media-radio>
              </template>
            </media-audio-radio-group>
          </media-menu-items>
        </media-menu>
      `})})}function qs(){return o(()=>{const{noAudioGain:e,translations:t}=u(),{canSetAudioGain:s}=x();if(_(()=>!s()||e())())return null;const{audioGain:a}=x();return P({label:d(t,"Boost"),value:o(()=>Math.round(((a()??1)-1)*100)+"%"),children:[ot({upIcon:"menu-audio-boost-up",downIcon:"menu-audio-boost-down",children:zs(),isMin:()=>((a()??1)-1)*100<=we(),isMax:()=>((a()??1)-1)*100===Te()})]})})}function zs(){const{translations:e}=u(),t=d(e,"Boost"),s=we,n=Te,a=Us;return i`
    <media-audio-gain-slider
      class="vds-audio-gain-slider vds-slider"
      aria-label=${t}
      min=${o(s)}
      max=${o(n)}
      step=${o(a)}
      key-step=${o(a)}
    >
      ${at()}${it()}
    </media-audio-gain-slider>
  `}function we(){const{audioGains:e}=u(),t=e();return B(t)?t[0]??0:t.min}function Te(){const{audioGains:e}=u(),t=e();return B(t)?t[t.length-1]??300:t.max}function Us(){const{audioGains:e}=u(),t=e();return B(t)?t[1]-t[0]||25:t.step}function js(){return o(()=>{const{translations:e}=u(),{hasCaptions:t}=x(),s=d(e,"Off");return t()?i`
      <media-menu class="vds-captions-menu vds-menu">
        ${N({label:()=>C(e,"Captions"),icon:"menu-captions"})}
        <media-menu-items class="vds-menu-items">
          <media-captions-radio-group
            class="vds-captions-radio-group vds-radio-group"
            off-label=${s}
          >
            <template>
              <media-radio class="vds-caption-radio vds-radio">
                <slot name="menu-radio-check-icon" data-class="vds-icon"></slot>
                <span class="vds-radio-label" data-part="label"></span>
              </media-radio>
            </template>
          </media-captions-radio-group>
        </media-menu-items>
      </media-menu>
    `:null})}function Zs(){return o(()=>{const{translations:e}=u();return i`
      <media-menu class="vds-playback-menu vds-menu">
        ${N({label:()=>C(e,"Playback"),icon:"menu-playback"})}
        <media-menu-items class="vds-menu-items">
          ${[P({children:Ys()}),Xs(),sn()]}
        </media-menu-items>
      </media-menu>
    `})}function Ys(){const{remote:e}=y(),{translations:t}=u(),s="Loop";return j({label:d(t,s),children:lt({label:s,storageKey:"vds-player::user-loop",onChange(n,a){e.userPrefersLoopChange(n,a)}})})}function Xs(){return o(()=>{const{translations:e}=u(),{canSetPlaybackRate:t,playbackRate:s}=x();return t()?P({label:d(e,"Speed"),value:o(()=>s()===1?C(e,"Normal"):s()+"x"),children:[ot({upIcon:"menu-speed-up",downIcon:"menu-speed-down",children:tn(),isMin:()=>s()===ke(),isMax:()=>s()===Se()})]}):null})}function ke(){const{playbackRates:e}=u(),t=e();return B(t)?t[0]??0:t.min}function Se(){const{playbackRates:e}=u(),t=e();return B(t)?t[t.length-1]??2:t.max}function Js(){const{playbackRates:e}=u(),t=e();return B(t)?t[1]-t[0]||.25:t.step}function tn(){const{translations:e}=u(),t=d(e,"Speed"),s=ke,n=Se,a=Js;return i`
    <media-speed-slider
      class="vds-speed-slider vds-slider"
      aria-label=${t}
      min=${o(s)}
      max=${o(n)}
      step=${o(a)}
      key-step=${o(a)}
    >
      ${at()}${it()}
    </media-speed-slider>
  `}function en(){const{remote:e,qualities:t}=y(),{autoQuality:s,canSetQuality:n,qualities:a}=x(),{translations:l}=u(),r="Auto";return _(()=>!n()||a().length<=1)()?null:j({label:d(l,r),children:lt({label:r,checked:s,onChange(p,v){p?e.requestAutoQuality(v):e.changeQuality(t.selectedIndex,v)}})})}function sn(){return o(()=>{const{hideQualityBitrate:e,translations:t}=u(),{canSetQuality:s,qualities:n,quality:a}=x(),l=_(()=>!s()||n().length<=1),r=_(()=>Ue(n()));return l()?null:P({label:d(t,"Quality"),value:o(()=>{var m,$;const c=(m=a())==null?void 0:m.height,p=e()?null:($=a())==null?void 0:$.bitrate,v=p&&p>0?`${(p/1e6).toFixed(2)} Mbps`:null,f=C(t,"Auto");return c?`${c}p${v?` (${v})`:""}`:f}),children:[ot({upIcon:"menu-quality-up",downIcon:"menu-quality-down",children:nn(),isMin:()=>r()[0]===a(),isMax:()=>r().at(-1)===a()}),en()]})})}function nn(){const{translations:e}=u(),t=d(e,"Quality");return i`
    <media-quality-slider class="vds-quality-slider vds-slider" aria-label=${t}>
      ${at()}${it()}
    </media-quality-slider>
  `}function Ce({placement:e,portal:t,tooltip:s}){return o(()=>{const{viewType:n}=x(),{translations:a,menuPortal:l,noModal:r,menuGroup:c,smallWhen:p}=u(),v=_(()=>r()?J(e):p()?null:J(e)),f=_(()=>!p()&&c()==="bottom"&&n()==="video"?26:0),m=T(!1);bs();function $(){m.set(!0)}function g(){m.set(!1)}const w=i`
      <media-menu-items
        class="vds-settings-menu-items vds-menu-items"
        placement=${o(v)}
        offset=${o(f)}
      >
        ${o(()=>m()?[Zs(),Es(),Hs(),js()]:null)}
      </media-menu-items>
    `;return i`
      <media-menu class="vds-settings-menu vds-menu" @open=${$} @close=${g}>
        <media-tooltip class="vds-tooltip">
          <media-tooltip-trigger>
            <media-menu-button
              class="vds-menu-button vds-button"
              aria-label=${d(a,"Settings")}
            >
              ${D("menu-settings","vds-rotate-icon")}
            </media-menu-button>
          </media-tooltip-trigger>
          <media-tooltip-content
            class="vds-tooltip-content"
            placement=${_t(s)?o(s):s}
          >
            ${d(a,"Settings")}
          </media-tooltip-content>
        </media-tooltip>
        ${t?ge(l,w):w}
      </media-menu>
    `})}function Bt({orientation:e,tooltip:t}){return o(()=>{const{pointer:s,muted:n,canSetVolume:a}=x();if(s()==="coarse"&&!n())return null;if(!a())return qt({tooltip:t});const l=T(void 0),r=qe(l);return i`
      <div class="vds-volume" ?data-active=${o(r)} ${st(l.set)}>
        ${qt({tooltip:t})}
        <div class="vds-volume-popup">${an({orientation:e})}</div>
      </div>
    `})}function an({orientation:e}={}){const{translations:t}=u(),s=d(t,"Volume");return i`
    <media-volume-slider
      class="vds-volume-slider vds-slider"
      aria-label=${s}
      orientation=${St(e)}
    >
      <div class="vds-slider-track"></div>
      <div class="vds-slider-track-fill vds-slider-track"></div>
      <media-slider-preview class="vds-slider-preview" no-clamp>
        <media-slider-value class="vds-slider-value"></media-slider-value>
      </media-slider-preview>
      <div class="vds-slider-thumb"></div>
    </media-volume-slider>
  `}function Gt(){const e=T(void 0),t=T(0),{thumbnails:s,translations:n,sliderChaptersMinWidth:a,disableTimeSlider:l,seekStep:r,noScrubGesture:c}=u(),p=d(n,"Seek"),v=o(l),f=o(()=>t()<a()),m=o(s);return ne(e,()=>{const $=e();$&&t.set($.clientWidth)}),i`
    <media-time-slider
      class="vds-time-slider vds-slider"
      aria-label=${p}
      key-step=${o(r)}
      ?disabled=${v}
      ?no-swipe-gesture=${o(c)}
      ${st(e.set)}
    >
      <media-slider-chapters class="vds-slider-chapters" ?disabled=${f}>
        <template>
          <div class="vds-slider-chapter">
            <div class="vds-slider-track"></div>
            <div class="vds-slider-track-fill vds-slider-track"></div>
            <div class="vds-slider-progress vds-slider-track"></div>
          </div>
        </template>
      </media-slider-chapters>
      <div class="vds-slider-thumb"></div>
      <media-slider-preview class="vds-slider-preview">
        <media-slider-thumbnail
          class="vds-slider-thumbnail vds-thumbnail"
          .src=${m}
        ></media-slider-thumbnail>
        <div class="vds-slider-chapter-title" data-part="chapter-title"></div>
        <media-slider-value class="vds-slider-value"></media-slider-value>
      </media-slider-preview>
    </media-time-slider>
  `}function on(){return i`
    <div class="vds-time-group">
      ${o(()=>{const{duration:e}=x();return e()?[i`<media-time class="vds-time" type="current"></media-time>`,i`<div class="vds-time-divider">/</div>`,i`<media-time class="vds-time" type="duration"></media-time>`]:null})}
    </div>
  `}function ln(){return o(()=>{const{live:e,duration:t}=x();return e()?ye():t()?i`<media-time class="vds-time" type="current" toggle remainder></media-time>`:null})}function De(){return o(()=>{const{live:e}=x();return e()?ye():on()})}function Pe(){return o(()=>{const{textTracks:e}=y(),{title:t,started:s}=x(),n=T(null);return oe(e,"chapters",n.set),n()&&(s()||!t())?Ae():i`<media-title class="vds-chapter-title"></media-title>`})}function Ae(){return i`<media-chapter-title class="vds-chapter-title"></media-chapter-title>`}class Me extends me{async Pf(){const t=(await re(()=>import("./vidstack-CRlTZX3Z-YlNA3hI6.js"),__vite__mapDeps([0,1]))).icons,s={};for(const n of Object.keys(t))s[n]=pe({name:n,paths:t[n]});return s}}var F;let rn=(F=class extends W{},F.props={...dt(F,F,"props"),when:({viewType:t})=>t==="audio",smallWhen:({width:t})=>t<576},F);function un(){return[Pt(),It(),i`
      <media-controls class="vds-controls">
        <media-controls-group class="vds-controls-group">
          ${[zt({backward:!0,tooltip:"top start"}),nt({tooltip:"top"}),zt({tooltip:"top"}),cn(),Gt(),ln(),Bt({orientation:"vertical",tooltip:"top"}),Mt({tooltip:"top"}),Ot(),At({tooltip:"top"}),dn()]}
        </media-controls-group>
      </media-controls>
    `]}function cn(){return o(()=>{let e=T(void 0),t=T(!1),s=y(),{title:n,started:a,currentTime:l,ended:r}=x(),{translations:c}=u(),p=ns(e),v=()=>a()||l()>0;const f=()=>{const g=r()?"Replay":v()?"Continue":"Play";return`${C(c,g)}: ${n()}`};S(()=>{var g;p()&&document.activeElement===document.body&&((g=s.player.el)==null||g.focus())});function m(){const g=e(),w=!!g&&!p()&&g.clientWidth<g.children[0].clientWidth;g&&as(g,"vds-marquee",w),t.set(w)}function $(){return i`
        <span class="vds-title-text">
          ${o(f)}${o(()=>v()?Ae():null)}
        </span>
      `}return ne(e,m),n()?i`
          <span class="vds-title" title=${o(f)} ${st(e.set)}>
            ${[$(),o(()=>t()&&!p()?$():null)]}
          </span>
        `:O()})}function dn(){const e="top end";return[xe({tooltip:"top",placement:e,portal:!0}),Ce({tooltip:"top end",placement:e,portal:!0})]}const tt=class tt extends Tt(Ct,rn){constructor(){super(...arguments),this.en=T(!1)}onSetup(){this.forwardKeepAlive=!1,this.a=y(),this.classList.add("vds-audio-layout"),this.Fn()}onConnect(){$e("audio",()=>this.isMatch),this.En()}render(){return o(this.Zm.bind(this))}Zm(){return this.isMatch?un():null}En(){const{menuPortal:t}=u();S(()=>{if(!this.isMatch)return;const s=_e(this.menuContainer,"vds-audio-layout",()=>this.isSmallLayout),n=s?[this,s]:[this];return(this.$props.customIcons()?new Dt(n):new Me(n)).connect(),t.set(s),()=>{s.remove(),t.set(null)}})}Fn(){const{pointer:t}=this.a.$state;S(()=>{t()==="coarse"&&S(this.rn.bind(this))})}rn(){if(!this.en()){Y(this,"pointerdown",this.sn.bind(this),{capture:!0});return}Y(this,"pointerdown",t=>t.stopPropagation()),Y(window,"pointerdown",this.tn.bind(this))}sn(t){const{target:s}=t;ss(s)&&s.closest(".vds-time-slider")&&(t.stopImmediatePropagation(),this.setAttribute("data-scrubbing",""),this.en.set(!0))}tn(){this.en.set(!1),this.removeAttribute("data-scrubbing")}};tt.tagName="media-audio-layout",tt.attrs={smallWhen:{converter(t){return t!=="never"&&!!t}}};let $t=tt;const q=class q extends W{};q.props={...dt(q,q,"props"),when:({viewType:t})=>t==="video",smallWhen:({width:t,height:s})=>t<576||s<380};let bt=q;function Oe(){return o(()=>{const e=y(),{noKeyboardAnimations:t,userPrefersKeyboardAnimations:s}=u();if(_(()=>t()||!s())())return null;const a=T(!1),{lastKeyboardAction:l}=e.$state;S(()=>{a.set(!!l());const m=setTimeout(()=>a.set(!1),500);return()=>{a.set(!1),window.clearTimeout(m)}});const r=_(()=>{var $;const m=($=l())==null?void 0:$.action;return m&&a()?X(m):null}),c=_(()=>`vds-kb-action${a()?"":" hidden"}`),p=_(pn),v=_(()=>{const m=mn();return m?is(m):null});function f(){const m=v();return m?i`
        <div class="vds-kb-bezel">
          <div class="vds-kb-icon">${m}</div>
        </div>
      `:null}return i`
      <div class=${o(c)} data-action=${o(r)}>
        <div class="vds-kb-text-wrapper">
          <div class="vds-kb-text">${o(p)}</div>
        </div>
        ${o(()=>cs(l(),f()))}
      </div>
    `})}function pn(){var n;const{$state:e}=y(),t=(n=e.lastKeyboardAction())==null?void 0:n.action,s=e.audioGain()??1;switch(t){case"toggleMuted":return e.muted()?"0%":Ut(e.volume(),s);case"volumeUp":case"volumeDown":return Ut(e.volume(),s);default:return""}}function Ut(e,t){return`${Math.round(e*t*100)}%`}function mn(){var s;const{$state:e}=y();switch((s=e.lastKeyboardAction())==null?void 0:s.action){case"togglePaused":return e.paused()?"kb-pause-icon":"kb-play-icon";case"toggleMuted":return e.muted()||e.volume()===0?"kb-mute-icon":e.volume()>=.5?"kb-volume-up-icon":"kb-volume-down-icon";case"toggleFullscreen":return`kb-fs-${e.fullscreen()?"enter":"exit"}-icon`;case"togglePictureInPicture":return`kb-pip-${e.pictureInPicture()?"enter":"exit"}-icon`;case"toggleCaptions":return e.hasCaptions()?`kb-cc-${e.textTrack()?"on":"off"}-icon`:null;case"volumeUp":return"kb-volume-up-icon";case"volumeDown":return"kb-volume-down-icon";case"seekForward":return"kb-seek-forward-icon";case"seekBackward":return"kb-seek-backward-icon";default:return null}}function vn(){return[Pt(),Ie(),rt(),Oe(),It(),i`<div class="vds-scrim"></div>`,i`
      <media-controls class="vds-controls">
        ${[$n(),O(),i`<media-controls-group class="vds-controls-group"></media-controls-group>`,O(),i`
            <media-controls-group class="vds-controls-group">
              ${Gt()}
            </media-controls-group>
          `,i`
            <media-controls-group class="vds-controls-group">
              ${[nt({tooltip:"top start"}),Bt({orientation:"horizontal",tooltip:"top"}),De(),Pe(),Mt({tooltip:"top"}),fn(),At({tooltip:"top"}),be({tooltip:"top"}),Ot(),ks(),he({tooltip:"top end"})]}
            </media-controls-group>
          `]}
      </media-controls>
    `]}function fn(){return o(()=>{const{menuGroup:e}=u();return e()==="bottom"?Ft():null})}function $n(){return i`
    <media-controls-group class="vds-controls-group">
      ${o(()=>{const{menuGroup:e}=u();return e()==="top"?[O(),Ft()]:null})}
    </media-controls-group>
  `}function bn(){return[Pt(),Ie(),rt(),It(),Oe(),i`<div class="vds-scrim"></div>`,i`
      <media-controls class="vds-controls">
        <media-controls-group class="vds-controls-group">
          ${[At({tooltip:"top start"}),be({tooltip:"bottom start"}),O(),Mt({tooltip:"bottom"}),Ot(),Ft(),Bt({orientation:"vertical",tooltip:"bottom end"})]}
        </media-controls-group>

        ${O()}

        <media-controls-group class="vds-controls-group" style="pointer-events: none;">
          ${[O(),nt({tooltip:"top"}),O()]}
        </media-controls-group>

        ${O()}

        <media-controls-group class="vds-controls-group">
          ${[De(),Pe(),he({tooltip:"top end"})]}
        </media-controls-group>

        <media-controls-group class="vds-controls-group">
          ${Gt()}
        </media-controls-group>
      </media-controls>
    `,yn()]}function hn(){return i`
    <div class="vds-load-container">
      ${[rt(),nt({tooltip:"top"})]}
    </div>
  `}function yn(){return o(()=>{const{duration:e}=x();return e()===0?null:i`
      <div class="vds-start-duration">
        <media-time class="vds-time" type="duration"></media-time>
      </div>
    `})}function rt(){return i`
    <div class="vds-buffering-indicator">
      <media-spinner class="vds-buffering-spinner"></media-spinner>
    </div>
  `}function Ft(){const{menuGroup:e,smallWhen:t}=u(),s=()=>e()==="top"||t()?"bottom":"top",n=_(()=>`${s()} ${e()==="top"?"end":"center"}`),a=_(()=>`${s()} end`);return[xe({tooltip:n,placement:a,portal:!0}),Ce({tooltip:n,placement:a,portal:!0})]}function Ie(){return o(()=>{const{noGestures:e}=u();return e()?null:i`
      <div class="vds-gestures">
        <media-gesture class="vds-gesture" event="pointerup" action="toggle:paused"></media-gesture>
        <media-gesture
          class="vds-gesture"
          event="pointerup"
          action="toggle:controls"
        ></media-gesture>
        <media-gesture
          class="vds-gesture"
          event="dblpointerup"
          action="toggle:fullscreen"
        ></media-gesture>
        <media-gesture class="vds-gesture" event="dblpointerup" action="seek:-10"></media-gesture>
        <media-gesture class="vds-gesture" event="dblpointerup" action="seek:10"></media-gesture>
      </div>
    `})}const et=class et extends Tt(Ct,bt){onSetup(){this.forwardKeepAlive=!1,this.a=y(),this.classList.add("vds-video-layout")}onConnect(){$e("video",()=>this.isMatch),this.En()}render(){return o(this.Zm.bind(this))}En(){const{menuPortal:t}=u();S(()=>{if(!this.isMatch)return;const s=_e(this.menuContainer,"vds-video-layout",()=>this.isSmallLayout),n=s?[this,s]:[this];return(this.$props.customIcons()?new Dt(n):new Me(n)).connect(),t.set(s),()=>{s.remove(),t.set(null)}})}Zm(){const{load:t}=this.a.$props,{canLoad:s,streamType:n,nativeControls:a}=this.a.$state;return!a()&&this.isMatch?t()==="play"&&!s()?hn():n()==="unknown"?rt():this.isSmallLayout?bn():vn():null}};et.tagName="media-video-layout",et.attrs={smallWhen:{converter(t){return t!=="never"&&!!t}}};let ht=et;class gn extends me{async Pf(){const t=(await re(()=>import("./vidstack-DXxIKXmd-pmgBYJSv.js"),__vite__mapDeps([]))).icons,s={};for(const n of Object.keys(t))s[n]=pe({name:n,paths:t[n],viewBox:"0 0 18 18"});return s}}function ut(e,t){var s;return((s=e())==null?void 0:s[t])??t}function _n(){return kn()}function xn(){const e=y(),{load:t}=e.$props,{canLoad:s}=e.$state;return _(()=>t()==="play"&&!s())()?[Le(),jt()]:[wn(),Tn(),jt(),Sn(),En(),Wn()]}function Le(){const e=y(),{translations:t}=b(),{title:s}=e.$state,n=o(()=>`${ut(t,"Play")}, ${s()}`);return i`
    <media-play-button
      class="plyr__control plyr__control--overlaid"
      aria-label=${n}
      data-plyr="play"
    >
      <slot name="play-icon"></slot>
    </button>
  `}function wn(){const{controls:e}=b();return o(()=>e().includes("play-large")?Le():null)}function Tn(){const{thumbnails:e,previewTime:t}=b();return i`
    <media-thumbnail
      .src=${o(e)}
      class="plyr__preview-scrubbing"
      time=${o(()=>t())}
    ></media-thumbnail>
  `}function jt(){const e=y(),{poster:t}=e.$state,s=o(()=>`background-image: url("${t()}");`);return i`<div class="plyr__poster" style=${s}></div>`}function kn(){const e=new Set(["captions","pip","airplay","fullscreen"]),{controls:t}=b(),s=o(()=>t().filter(n=>!e.has(n)).map(Be));return i`<div class="plyr__controls">${s}</div>`}function Sn(){const{controls:e}=b(),t=o(()=>e().map(Be));return i`<div class="plyr__controls">${t}</div>`}function Be(e){switch(e){case"airplay":return Cn();case"captions":return Dn();case"current-time":return Rn();case"download":return Vn();case"duration":return Ge();case"fast-forward":return Bn();case"fullscreen":return Pn();case"mute":case"volume":case"mute+volume":return Fn(e);case"pip":return Mn();case"play":return On();case"progress":return Gn();case"restart":return In();case"rewind":return Ln();case"settings":return Kn();default:return null}}function Cn(){const{translations:e}=b();return i`
    <media-airplay-button class="plyr__controls__item plyr__control" data-plyr="airplay">
      <slot name="airplay-icon"></slot>
      <span class="plyr__tooltip">${h(e,"AirPlay")}</span>
    </media-airplay-button>
  `}function Dn(){const{translations:e}=b(),t=h(e,"Disable captions"),s=h(e,"Enable captions");return i`
    <media-caption-button
      class="plyr__controls__item plyr__control"
      data-no-label
      data-plyr="captions"
    >
      <slot name="captions-on-icon" data-class="icon--pressed"></slot>
      <slot name="captions-off-icon" data-class="icon--not-pressed"></slot>
      <span class="label--pressed plyr__tooltip">${t}</span>
      <span class="label--not-pressed plyr__tooltip">${s}</span>
    </media-caption-button>
  `}function Pn(){const{translations:e}=b(),t=h(e,"Enter Fullscreen"),s=h(e,"Exit Fullscreen");return i`
    <media-fullscreen-button
      class="plyr__controls__item plyr__control"
      data-no-label
      data-plyr="fullscreen"
    >
      <slot name="enter-fullscreen-icon" data-class="icon--pressed"></slot>
      <slot name="exit-fullscreen-icon" data-class="icon--not-pressed"></slot>
      <span class="label--pressed plyr__tooltip">${s}</span>
      <span class="label--not-pressed plyr__tooltip">${t}</span>
    </media-fullscreen-button>
  `}function An(){const{translations:e}=b(),t=h(e,"Mute"),s=h(e,"Unmute");return i`
    <media-mute-button class="plyr__control" data-no-label data-plyr="mute">
      <slot name="muted-icon" data-class="icon--pressed"></slot>
      <slot name="volume-icon" data-class="icon--not-pressed"></slot>
      <span class="label--pressed plyr__tooltip">${s}</span>
      <span class="label--not-pressed plyr__tooltip">${t}</span>
    </media-mute-button>
  `}function Mn(){const{translations:e}=b(),t=h(e,"Enter PiP"),s=h(e,"Exit PiP");return i`
    <media-pip-button class="plyr__controls__item plyr__control" data-no-label data-plyr="pip">
      <slot name="pip-icon"></slot>
      <slot name="enter-pip-icon" data-class="icon--pressed"></slot>
      <slot name="exit-pip-icon" data-class="icon--not-pressed"></slot>
      <span class="label--pressed plyr__tooltip">${s}</span>
      <span class="label--not-pressed plyr__tooltip">${t}</span>
    </media-pip-button>
  `}function On(){const{translations:e}=b(),t=h(e,"Play"),s=h(e,"Pause");return i`
    <media-play-button class="plyr__controls__item plyr__control" data-no-label data-plyr="play">
      <slot name="pause-icon" data-class="icon--pressed"></slot>
      <slot name="play-icon" data-class="icon--not-pressed"></slot>
      <span class="label--pressed plyr__tooltip">${s}</span>
      <span class="label--not-pressed plyr__tooltip">${t}</span>
    </media-play-button>
  `}function In(){const{translations:e}=b(),{remote:t}=y(),s=h(e,"Restart");function n(a){le(a)&&!wt(a)||t.seek(0,a)}return i`
    <button
      type="button"
      class="plyr__control"
      data-plyr="restart"
      @pointerup=${n}
      @keydown=${n}
    >
      <slot name="restart-icon"></slot>
      <span class="plyr__tooltip">${s}</span>
    </button>
  `}function Ln(){const{translations:e,seekTime:t}=b(),s=o(()=>`${ut(e,"Rewind")} ${t()}s`),n=o(()=>-1*t());return i`
    <media-seek-button
      class="plyr__controls__item plyr__control"
      seconds=${n}
      data-no-label
      data-plyr="rewind"
    >
      <slot name="rewind-icon"></slot>
      <span class="plyr__tooltip">${s}</span>
    </media-seek-button>
  `}function Bn(){const{translations:e,seekTime:t}=b(),s=o(()=>`${ut(e,"Forward")} ${t()}s`),n=o(t);return i`
    <media-seek-button
      class="plyr__controls__item plyr__control"
      seconds=${n}
      data-no-label
      data-plyr="fast-forward"
    >
      <slot name="fast-forward-icon"></slot>
      <span class="plyr__tooltip">${s}</span>
    </media-seek-button>
  `}function Gn(){let e=y(),{duration:t,viewType:s}=e.$state,{translations:n,markers:a,thumbnails:l,seekTime:r,previewTime:c}=b(),p=h(n,"Seek"),v=T(null),f=o(()=>{const k=v();return k?i`<span class="plyr__progress__marker-label">${ue(k.label)}<br /></span>`:null});function m(k){c.set(k.detail)}function $(){v.set(this)}function g(){v.set(null)}function w(){const k=l(),L=o(()=>s()==="audio");return k?i`
          <media-slider-preview class="plyr__slider__preview" ?no-clamp=${L}>
            <media-slider-thumbnail .src=${k} class="plyr__slider__preview__thumbnail">
              <span class="plyr__slider__preview__time-container">
                ${f}
                <media-slider-value class="plyr__slider__preview__time"></media-slider-value>
              </span>
            </media-slider-thumbnail>
          </media-slider-preview>
        `:i`
          <span class="plyr__tooltip">
            ${f}
            <media-slider-value></media-slider-value>
          </span>
        `}function A(){var L;const k=t();return Number.isFinite(k)?(L=a())==null?void 0:L.map(G=>i`
        <span
          class="plyr__progress__marker"
          @mouseenter=${$.bind(G)}
          @mouseleave=${g}
          style=${`left: ${G.time/k*100}%;`}
        ></span>
      `):null}return i`
    <div class="plyr__controls__item plyr__progress__container">
      <div class="plyr__progress">
        <media-time-slider
          class="plyr__slider"
          data-plyr="seek"
          pause-while-dragging
          key-step=${o(r)}
          aria-label=${p}
          @media-seeking-request=${m}
        >
          <div class="plyr__slider__track"></div>
          <div class="plyr__slider__thumb"></div>
          <div class="plyr__slider__buffer"></div>
          ${o(w)}${o(A)}
        </media-time-slider>
      </div>
    </div>
  `}function Fn(e){return o(()=>{const t=e==="mute"||e==="mute+volume",s=e==="volume"||e==="mute+volume";return i`
      <div class="plyr__controls__item plyr__volume">
        ${[t?An():null,s?Nn():null]}
      </div>
    `})}function Nn(){const{translations:e}=b(),t=h(e,"Volume");return i`
    <media-volume-slider class="plyr__slider" data-plyr="volume" aria-label=${t}>
      <div class="plyr__slider__track"></div>
      <div class="plyr__slider__thumb"></div>
    </media-volume-slider>
  `}function Rn(){const e=y(),{translations:t,invertTime:s,toggleTime:n,displayDuration:a}=b(),l=T(gt(s));function r(p){!n()||a()||le(p)&&!wt(p)||l.set(v=>!v)}function c(){return o(()=>a()?Ge():null)}return o(()=>{const{streamType:p}=e.$state,v=h(t,"LIVE"),f=h(t,"Current time"),m=o(()=>!a()&&l());return p()==="live"||p()==="ll-live"?i`
          <media-live-button
            class="plyr__controls__item plyr__control plyr__live-button"
            data-plyr="live"
          >
            <span class="plyr__live-button__text">${v}</span>
          </media-live-button>
        `:i`
          <media-time
            type="current"
            class="plyr__controls__item plyr__time plyr__time--current"
            tabindex="0"
            role="timer"
            aria-label=${f}
            ?remainder=${m}
            @pointerup=${r}
            @keydown=${r}
          ></media-time>
          ${c()}
        `})}function Ge(){const{translations:e}=b(),t=h(e,"Duration");return i`
    <media-time
      type="duration"
      class="plyr__controls__item plyr__time plyr__time--duration"
      role="timer"
      tabindex="0"
      aria-label=${t}
    ></media-time>
  `}function Vn(){return o(()=>{const e=y(),{translations:t,download:s}=b(),{title:n,source:a}=e.$state,l=a(),r=s(),c=ie({title:n(),src:l,download:r}),p=h(t,"Download");return c?i`
          <a
            class="plyr__controls__item plyr__control"
            href=${c.url+`?download=${c.name}`}
            download=${c.name}
            target="_blank"
          >
            <slot name="download-icon" />
            <span class="plyr__tooltip">${p}</span>
          </a>
        `:null})}function En(){return o(()=>{const{clickToPlay:e,clickToFullscreen:t}=b();return[e()?i`
            <media-gesture
              class="plyr__gesture"
              event="pointerup"
              action="toggle:paused"
            ></media-gesture>
          `:null,t()?i`
            <media-gesture
              class="plyr__gesture"
              event="dblpointerup"
              action="toggle:fullscreen"
            ></media-gesture>
          `:null]})}function Wn(){const e=y(),t=T(void 0),s=o(()=>{var n;return ue((n=t())==null?void 0:n.text)});return S(()=>{const n=e.$state.textTrack();if(!n)return;function a(){t.set(n==null?void 0:n.activeCues[0])}return a(),Y(n,"cue-change",a)}),i`
    <div class="plyr__captions" dir="auto">
      <span class="plyr__caption">${s}</span>
    </div>
  `}function Kn(){const{translations:e}=b(),t=h(e,"Settings");return i`
    <div class="plyr__controls__item plyr__menu">
      <media-menu>
        <media-menu-button class="plyr__control" data-plyr="settings">
          <slot name="settings-icon" />
          <span class="plyr__tooltip">${t}</span>
        </media-menu-button>
        <media-menu-items class="plyr__menu__container" placement="top end">
          <div><div>${[Qn(),jn(),Yn(),zn()]}</div></div>
        </media-menu-items>
      </media-menu>
    </div>
  `}function ct({label:e,children:t}){const s=T(!1);return i`
    <media-menu @open=${()=>s.set(!0)} @close=${()=>s.set(!1)}>
      ${Hn({label:e,open:s})}
      <media-menu-items>${t}</media-menu-items>
    </media-menu>
  `}function Hn({open:e,label:t}){const{translations:s}=b(),n=o(()=>`plyr__control plyr__control--${e()?"back":"forward"}`);function a(){const l=h(s,"Go back to previous menu");return o(()=>e()?i`<span class="plyr__sr-only">${l}</span>`:null)}return i`
    <media-menu-button class=${n} data-plyr="settings">
      <span class="plyr__menu__label" aria-hidden=${Jn(e)}>
        ${h(s,t)}
      </span>
      <span class="plyr__menu__value" data-part="hint"></span>
      ${a()}
    </media-menu-button>
  `}function Qn(){return ct({label:"Audio",children:qn()})}function qn(){const{translations:e}=b();return i`
    <media-audio-radio-group empty-label=${h(e,"Default")}>
      <template>
        <media-radio class="plyr__control" data-plyr="audio">
          <span data-part="label"></span>
        </media-radio>
      </template>
    </media-audio-radio-group>
  `}function zn(){return ct({label:"Speed",children:Un()})}function Un(){const{translations:e,speed:t}=b();return i`
    <media-speed-radio-group .rates=${t} normal-label=${h(e,"Normal")}>
      <template>
        <media-radio class="plyr__control" data-plyr="speed">
          <span data-part="label"></span>
        </media-radio>
      </template>
    </media-speed-radio-group>
  `}function jn(){return ct({label:"Captions",children:Zn()})}function Zn(){const{translations:e}=b();return i`
    <media-captions-radio-group off-label=${h(e,"Disabled")}>
      <template>
        <media-radio class="plyr__control" data-plyr="captions">
          <span data-part="label"></span>
        </media-radio>
      </template>
    </media-captions-radio-group>
  `}function Yn(){return ct({label:"Quality",children:Xn()})}function Xn(){const{translations:e}=b();return i`
    <media-quality-radio-group auto-label=${h(e,"Auto")}>
      <template>
        <media-radio class="plyr__control" data-plyr="quality">
          <span data-part="label"></span>
        </media-radio>
      </template>
    </media-quality-radio-group>
  `}function Jn(e){return o(()=>e()?"true":"false")}function h(e,t){return o(()=>ut(e,t))}const Vt=class Vt extends Tt(Ct,ft){onSetup(){this.forwardKeepAlive=!1,this.a=y()}onConnect(){var t;(t=this.a.player.el)==null||t.setAttribute("data-layout","plyr"),E(()=>{var s;return(s=this.a.player.el)==null?void 0:s.removeAttribute("data-layout")}),ps(this,this.a),S(()=>{this.$props.customIcons()?new Dt([this]).connect():new gn([this]).connect()})}render(){return o(this.Zm.bind(this))}Zm(){const{viewType:t}=this.a.$state;return t()==="audio"?_n():t()==="video"?xn():null}};Vt.tagName="media-plyr-layout";let yt=Vt;kt($t);kt(ht);kt(yt);
function __vite__mapDeps(indexes) {
  if (!__vite__mapDeps.viteFileDeps) {
    __vite__mapDeps.viteFileDeps = ["assets/vidstack-CRlTZX3Z-YlNA3hI6.js","assets/vidstack-DQ6dSZwe-FUyOJuAD.js"]
  }
  return indexes.map((i) => __vite__mapDeps.viteFileDeps[i])
}
