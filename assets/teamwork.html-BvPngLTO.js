import{_ as i}from"./refreshProjectConfig-BwiCzY9v.js";import{_ as s,a as l}from"./transferData-3Hz9rl_T.js";import{_ as d,e as p,g as t,f as m,j as o,h as a,i as n,r as u,o as c}from"./app-CXCh97sD.js";const h="/img/2023.1.3/teamDirectory_en.png",g={};function f(y,e){const r=u("Badge");return c(),p("div",null,[t("p",null,[e[0]||(e[0]=o("Version Required: ")),a(r,{text:"2023.1.3",type:"error"})]),e[9]||(e[9]=t("h2",{id:"principle",tabindex:"-1"},[t("a",{class:"header-anchor",href:"#principle"},[t("span",null,"Principle")])],-1)),e[10]||(e[10]=t("p",null,[o("Based on version control systems, share "),t("strong",null,"configurations"),o(" and "),t("strong",null,"API data"),o(".")],-1)),e[11]||(e[11]=t("h2",{id:"features",tabindex:"-1"},[t("a",{class:"header-anchor",href:"#features"},[t("span",null,"Features")])],-1)),t("ul",null,[t("li",null,[a(r,{bgColor:"#fe2857"},{default:n(()=>e[1]||(e[1]=[o("No deployment required")])),_:1})]),t("li",null,[a(r,{bgColor:"#087CFA"},{default:n(()=>e[2]||(e[2]=[o("Privatisation")])),_:1})]),t("li",null,[a(r,{bgColor:"#FE2857"},{default:n(()=>e[3]||(e[3]=[o("Unlimited developers")])),_:1})]),t("li",null,[a(r,{bgColor:"#FC801D"},{default:n(()=>e[4]||(e[4]=[o("Unlimited APIs")])),_:1})]),t("li",null,[a(r,{bgColor:"#6B57FF"},{default:n(()=>e[5]||(e[5]=[o("Unlimited projects")])),_:1})]),t("li",null,[a(r,{bgColor:"#3DEA62"},{default:n(()=>e[6]||(e[6]=[o("Unlimited features")])),_:1})]),t("li",null,[a(r,{bgColor:"#FDB60D"},{default:n(()=>e[7]||(e[7]=[o("Offline work")])),_:1})]),t("li",null,[a(r,{bgColor:"#21D789"},{default:n(()=>e[8]||(e[8]=[o("Safe")])),_:1})])]),e[12]||(e[12]=m('<h2 id="how-to-use" tabindex="-1"><a class="header-anchor" href="#how-to-use"><span>How to use</span></a></h2><p>Based on the principle, so when we need to share or pull someone else&#39;s configuration or API, assuming we use Git as our version control system, we can just <strong>push</strong> to share configuration and API data, and <strong>pull</strong> to get someone else&#39;s committed configuration and API data.</p><h3 id="update-configuration" tabindex="-1"><a class="header-anchor" href="#update-configuration"><span>Update configuration</span></a></h3><p>After pull, click the Refresh Project Configuration button to get the configuration submitted by other members.</p><p><img src="'+i+'" alt="refreshProjectConfig" loading="lazy"></p><h2 id="update-api" tabindex="-1"><a class="header-anchor" href="#update-api"><span>Update API</span></a></h2><p>After pull, for example <code>Git pull</code> ,click the API Refresh button to get the APIs submitted by other members.</p><p><img src="'+s+'" alt="refreshProjectConfig" loading="lazy"></p><h2 id="local-file-storage-structure" tabindex="-1"><a class="header-anchor" href="#local-file-storage-structure"><span>Local file storage structure</span></a></h2><p>By default, the plugin will create a <strong>.fastRequest</strong> directory in the root directory of the project. APIs are stored in the <strong>collections</strong> directory, and configurations are stored in the <strong>config</strong> directory. APIs are stored in the structure of <code>module-&gt;class name-&gt;API</code>, and each directory contains a <strong>directory.json</strong> file to identify the directory. Other APIs are stored using the <strong>methodName.rapi</strong>, and conflicts may occur in this file due to multiple modifications in version control systems. Users need to resolve the conflicts and meet the standard JSON and data structures.</p><p>Note that if you want to view the historical data of APIs, you can use the file history records of the version control system to restore them, or you can use the local file history records to restore them.</p><p><img src="'+h+'" alt="teamDirectory" loading="lazy"></p><h2 id="transfer-historical-data" tabindex="-1"><a class="header-anchor" href="#transfer-historical-data"><span>Transfer historical data.</span></a></h2><p>By default, the plugin will perform a historical data migration. During the data migration (for data on versions &lt;= 2023.1.3), you do not need to worry about historical data if your data has not been committed to the version control system. If someone else has already committed the API to the version control system, it will be overwritten. Note that if you want to view API historical data, you can restore it by combining the file history records of the version control system or the local file.</p><p>If you find that the data has not been migrated, you can also manually operate it.</p><p><img src="'+l+'" alt="transferData" loading="lazy"></p>',16))])}const A=d(g,[["render",f],["__file","teamwork.html.vue"]]),v=JSON.parse('{"path":"/en/guide/teamwork.html","title":"Teamwork","lang":"en-US","frontmatter":{"title":"Teamwork","icon":"teamwork","description":"Version Required: Principle Based on version control systems, share configurations and API data. Features How to use Based on the principle, so when we need to share or pull som...","head":[["link",{"rel":"alternate","hreflang":"zh-cn","href":"https://api-buddy.com/guide/teamwork.html"}],["meta",{"property":"og:url","content":"https://api-buddy.com/en/guide/teamwork.html"}],["meta",{"property":"og:site_name","content":"Fast Request"}],["meta",{"property":"og:title","content":"Teamwork"}],["meta",{"property":"og:description","content":"Version Required: Principle Based on version control systems, share configurations and API data. Features How to use Based on the principle, so when we need to share or pull som..."}],["meta",{"property":"og:type","content":"article"}],["meta",{"property":"og:image","content":"https://api-buddy.com/img/2023.1.3/refreshProjectConfig.png"}],["meta",{"property":"og:locale","content":"en-US"}],["meta",{"property":"og:locale:alternate","content":"zh-CN"}],["meta",{"property":"og:updated_time","content":"2024-12-15T11:00:59.000Z"}],["meta",{"property":"article:modified_time","content":"2024-12-15T11:00:59.000Z"}],["script",{"type":"application/ld+json"},"{\\"@context\\":\\"https://schema.org\\",\\"@type\\":\\"Article\\",\\"headline\\":\\"Teamwork\\",\\"image\\":[\\"https://api-buddy.com/img/2023.1.3/refreshProjectConfig.png\\",\\"https://api-buddy.com/img/2023.1.3/refreshAPI.png\\",\\"https://api-buddy.com/img/2023.1.3/teamDirectory_en.png\\",\\"https://api-buddy.com/img/2023.1.3/transferData.png\\"],\\"dateModified\\":\\"2024-12-15T11:00:59.000Z\\",\\"author\\":[]}"]]},"git":{"createdTime":1717147459000,"updatedTime":1734260459000,"contributors":[{"name":"Kings","username":"Kings","email":"963987632@qq.com","commits":1,"url":"https://github.com/Kings"},{"name":"Mister-Hope","username":"Mister-Hope","email":"mister-hope@outlook.com","commits":1,"url":"https://github.com/Mister-Hope"}]},"readingTime":{"minutes":1.36,"words":408},"filePathRelative":"en/guide/teamwork.md","localizedDate":"May 31, 2024","autoDesc":true}');export{A as comp,v as data};
