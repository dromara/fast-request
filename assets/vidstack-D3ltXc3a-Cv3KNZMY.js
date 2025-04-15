var I=Object.defineProperty;var x=i=>{throw TypeError(i)};var M=(i,n,t)=>n in i?I(i,n,{enumerable:!0,configurable:!0,writable:!0,value:t}):i[n]=t;var g=(i,n,t)=>M(i,typeof n!="symbol"?n+"":n,t),b=(i,n,t)=>n.has(i)||x("Cannot "+t);var o=(i,n,t)=>(b(i,n,"read from private field"),t?t.call(i):n.get(i)),u=(i,n,t)=>n.has(i)?x("Cannot add the same private member more than once"):n instanceof WeakSet?n.add(i):n.set(i,t),f=(i,n,t,e)=>(b(i,n,"write to private field"),e?e.call(i,t):n.set(i,t),t),r=(i,n,t)=>(b(i,n,"access private method"),t);import{D as Q,ai as T,z as Z,u as l,au as j,aZ as k,Q as m,ar as q,a6 as B}from"./app-CXCh97sD.js";var h,s,C,E,v,H,N,$,w,y,L,z,P,D;class O extends Q{constructor(){super(...arguments);u(this,s);u(this,h);u(this,$,"")}onSetup(){f(this,h,Z()),r(this,s,w).call(this),r(this,s,y).call(this),r(this,s,L).call(this),r(this,s,v).call(this)}onAttach(t){t.style.setProperty("pointer-events","none"),l(r(this,s,N).bind(this)),l(r(this,s,w).bind(this)),l(r(this,s,y).bind(this)),l(r(this,s,L).bind(this)),l(r(this,s,v).bind(this));const{started:e}=o(this,h).$state;this.setAttributes({"data-visible":()=>!e()&&!this.$state.hidden(),"data-loading":r(this,s,H).bind(this),"data-error":r(this,s,C).bind(this),"data-hidden":this.$state.hidden})}onConnect(t){l(r(this,s,E).bind(this)),l(r(this,s,z).bind(this))}}h=new WeakMap,s=new WeakSet,C=function(){const{error:t}=this.$state;return!j(t())},E=function(){const{canLoadPoster:t,poster:e}=o(this,h).$state;!t()&&e()&&k(e(),"preconnect")},v=function(){const{src:t}=this.$props,{poster:e,nativeControls:a}=o(this,h).$state;this.el&&m(this.el,"display",a()?"none":null),this.$state.hidden.set(r(this,s,C).call(this)||!(t()||e())||a())},H=function(){const{loading:t,hidden:e}=this.$state;return!e()&&t()},N=function(){const t=this.$state.img();t&&(new q(t).add("load",r(this,s,P).bind(this)).add("error",r(this,s,D).bind(this)),t.complete&&r(this,s,P).call(this))},$=new WeakMap,w=function(){const{poster:t}=o(this,h).$props,{canLoadPoster:e,providedPoster:a,inferredPoster:p}=o(this,h).$state,d=this.$props.src()||"",S=d||t()||p();o(this,$)===a()&&a.set(d),this.$state.src.set(e()&&S.length?S:null),f(this,$,d)},y=function(){const{src:t}=this.$props,{alt:e}=this.$state,{poster:a}=o(this,h).$state;e.set(t()||a()?this.$props.alt():null)},L=function(){const{crossOrigin:t}=this.$props,{crossOrigin:e}=this.$state,{crossOrigin:a,poster:p}=o(this,h).$state,d=t()!==null?t():a();e.set(/ytimg\.com|vimeo/.test(p()||"")?null:d===!0?"anonymous":d)},z=function(){const{loading:t,error:e}=this.$state,{canLoadPoster:a,poster:p}=o(this,h).$state;t.set(a()&&!!p()),e.set(null)},P=function(){const{loading:t,error:e}=this.$state;t.set(!1),e.set(null)},D=function(t){const{loading:e,error:a}=this.$state;e.set(!1),a.set(t)},g(O,"props",{src:null,alt:null,crossOrigin:null}),g(O,"state",new T({img:null,src:null,alt:null,crossOrigin:null,loading:!0,error:null,hidden:!1}));var c;class A extends B(HTMLElement,O){constructor(){super(...arguments);u(this,c,document.createElement("img"))}onSetup(){this.$state.img.set(o(this,c))}onConnect(){const{src:t,alt:e,crossOrigin:a}=this.$state;l(()=>{const{loading:p,hidden:d}=this.$state;o(this,c).style.display=p()||d()?"none":""}),l(()=>{m(o(this,c),"alt",e()),m(o(this,c),"crossorigin",a()),m(o(this,c),"src",t())}),o(this,c).parentNode!==this&&this.prepend(o(this,c))}}c=new WeakMap,g(A,"tagName","media-poster"),g(A,"attrs",{crossOrigin:"crossorigin"});export{A as MediaPosterElement};
