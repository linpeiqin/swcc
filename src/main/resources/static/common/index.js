!function (a) {
    var e = {};

    function i(t) {
        if (e[t]) return e[t].exports;
        var n = e[t] = {i: t, l: !1, exports: {}};
        return a[t].call(n.exports, n, n.exports, i), n.l = !0, n.exports
    }

    i.m = a, i.c = e, i.d = function (a, e, t) {
        i.o(a, e) || Object.defineProperty(a, e, {enumerable: !0, get: t})
    }, i.r = function (a) {
        "undefined" != typeof Symbol && Symbol.toStringTag && Object.defineProperty(a, Symbol.toStringTag, {value: "Module"}), Object.defineProperty(a, "__esModule", {value: !0})
    }, i.t = function (a, e) {
        if (1 & e && (a = i(a)), 8 & e) return a;
        if (4 & e && "object" == typeof a && a && a.__esModule) return a;
        var t = Object.create(null);
        if (i.r(t), Object.defineProperty(t, "default", {
            enumerable: !0,
            value: a
        }), 2 & e && "string" != typeof a) for (var n in a) i.d(t, n, function (e) {
            return a[e]
        }.bind(null, n));
        return t
    }, i.n = function (a) {
        var e = a && a.__esModule ? function () {
            return a.default
        } : function () {
            return a
        };
        return i.d(e, "a", e), e
    }, i.o = function (a, e) {
        return Object.prototype.hasOwnProperty.call(a, e)
    }, i.p = "", i(i.s = 14)
}({
    14: function (a, e, i) {
        i(15)
    }, 15: function (a, e) {
        layui.define(["layer", "element", "admin"], (function (a) {
            var e = layui.jquery, i = layui.layer, t = layui.element, n = layui.admin, l = n.setter,
                o = ".layui-layout-admin>.layui-header", u = ".layui-layout-admin>.layui-side>.layui-side-scroll",
                r = ".layui-layout-admin>.layui-body", s = r + ">.layui-tab", d = r + ">.layui-body-header",
                c = "admin-pagetabs", m = {}, y = !1, b = {
                    homeUrl: void 0, mTabPosition: void 0, mTabList: [], loadView: function (a) {
                        if (!a.menuPath) return i.msg("url不能为空", {icon: 2, anim: 6});
                        if (l.pageTabs) {
                            var o;
                            if (e(s + ">.layui-tab-title>li").each((function () {
                                e(this).attr("lay-id") === a.menuPath && (o = !0)
                            })), !o) {
                                if (b.mTabList.length + 1 >= l.maxTabNum) return i.msg("最多打开" + l.maxTabNum + "个选项卡", {
                                    icon: 2,
                                    anim: 6
                                }), n.activeNav(b.mTabPosition);
                                y = !0, t.tabAdd(c, {
                                    id: a.menuPath,
                                    title: '<span class="title">' + (a.menuName || "") + "</span>",
                                    content: '<iframe class="admin-iframe" lay-id="' + a.menuPath + '" src="' + a.menuPath + '" onload="layui.index.hideLoading(this);" frameborder="0"></iframe>'
                                }), n.showLoading({
                                    elem: e('iframe[lay-id="' + a.menuPath + '"]').parent(),
                                    size: ""
                                }), a.menuPath !== b.homeUrl && b.mTabList.push(a), l.cacheTab && n.putTempData("indexTabs", b.mTabList)
                            }
                            a.noChange || t.tabChange(c, a.menuPath)
                        } else {
                            n.activeNav(a.menuPath);
                            var d = e(r + ">div>.admin-iframe");
                            if (0 === d.length ? (e(r).html(['<div class="layui-body-header">', '   <span class="layui-body-header-title"></span>', '   <span class="layui-breadcrumb pull-right" lay-filter="admin-body-breadcrumb" style="visibility: visible;"></span>', "</div>", '<div style="-webkit-overflow-scrolling: touch;">', '   <iframe class="admin-iframe" lay-id="', a.menuPath, '" src="', a.menuPath, '"', '      onload="layui.index.hideLoading(this);" frameborder="0"></iframe>', "</div>"].join("")), n.showLoading({
                                elem: e('iframe[lay-id="' + a.menuPath + '"]').parent(),
                                size: ""
                            })) : (n.showLoading({
                                elem: d.parent(),
                                size: ""
                            }), d.attr("lay-id", a.menuPath).attr("src", a.menuPath)), e('[lay-filter="admin-body-breadcrumb"]').html(b.getBreadcrumbHtml(a.menuPath)), b.mTabList.splice(0, b.mTabList.length), a.menuPath === b.homeUrl ? (b.mTabPosition = void 0, b.setTabTitle(e(a.menuName).text() || e(u + ' [lay-href="' + b.homeUrl + '"]').text() || "主页")) : (b.mTabPosition = a.menuPath, b.mTabList.push(a), b.setTabTitle(a.menuName)), !l.cacheTab) return;
                            n.putTempData("indexTabs", b.mTabList), n.putTempData("tabPosition", b.mTabPosition)
                        }
                        n.getPageWidth() <= 768 && n.flexible(!0)
                    }, loadHome: function (a) {
                        var e = n.getTempData("indexTabs"), i = n.getTempData("tabPosition"),
                            t = (void 0 === a.loadSetting || a.loadSetting) && l.cacheTab && e && e.length > 0;
                        if (b.homeUrl = a.menuPath, a.noChange = !!i && t, !l.pageTabs && t || b.loadView(a), t) for (var o = 0; o < e.length; o++) e[o].noChange = e[o].menuPath !== i, (!e[o].noChange || l.pageTabs && !a.onlyLast) && b.loadView(e[o]);
                        n.removeLoading(void 0, !1)
                    }, openTab: function (a) {
                        if (window !== top && !n.isTop() && top.layui && top.layui.index) return top.layui.index.openTab(a);
                        a.end && (m[a.url] = a.end), b.loadView({menuPath: a.url, menuName: a.title})
                    }, closeTab: function (a) {
                        if (window !== top && !n.isTop() && top.layui && top.layui.index) return top.layui.index.closeTab(a);
                        t.tabDelete(c, a)
                    }, setTabCache: function (a) {
                        return window !== top && !n.isTop() && top.layui && top.layui.index ? top.layui.index.setTabCache(a) : (n.putSetting("cacheTab", a), a ? (n.putTempData("indexTabs", b.mTabList), void n.putTempData("tabPosition", b.mTabPosition)) : b.clearTabCache())
                    }, clearTabCache: function () {
                        n.putTempData("indexTabs", null), n.putTempData("tabPosition", null)
                    }, setTabTitle: function (a, i) {
                        if (window !== top && !n.isTop() && top.layui && top.layui.index) return top.layui.index.setTabTitle(a, i);
                        l.pageTabs ? (i || (i = e(s + ">.layui-tab-title>li.layui-this").attr("lay-id")), i && e(s + '>.layui-tab-title>li[lay-id="' + i + '"] .title').html(a || "")) : a ? (e(d + ">.layui-body-header-title").html(a), e(d).addClass("show"), e(o).css("box-shadow", "0 1px 0 0 rgba(0, 0, 0, .03)")) : (e(d).removeClass("show"), e(o).css("box-shadow", ""))
                    }, setTabTitleHtml: function (a) {
                        if (window !== top && !n.isTop() && top.layui && top.layui.index) return top.layui.index.setTabTitleHtml(a);
                        if (!l.pageTabs) {
                            if (!a) return e(d).removeClass("show");
                            e(d).html(a), e(d).addClass("show")
                        }
                    }, getBreadcrumb: function (a) {
                        a || (a = e(r + ">div>.admin-iframe").attr("lay-id"));
                        var i = [], t = e(u).find('[lay-href="' + a + '"]');
                        for (t.length > 0 && i.push(t.text().replace(/(^\s*)|(\s*$)/g, "")); 0 !== (t = t.parent("dd").parent("dl").prev("a")).length;) i.unshift(t.text().replace(/(^\s*)|(\s*$)/g, ""));
                        return i
                    }, getBreadcrumbHtml: function (a) {
                        for (var e = b.getBreadcrumb(a), i = a === b.homeUrl ? "" : '<a ew-href="' + b.homeUrl + '">首页</a>', t = 0; t < e.length - 1; t++) i && (i += '<span lay-separator="">/</span>'), i += "<a><cite>" + e[t] + "</cite></a>";
                        return i
                    }, hideLoading: function (a) {
                        "string" != typeof a && (a = e(a).attr("lay-id")), n.removeLoading(e('iframe[lay-id="' + a + '"],' + r + " iframe[lay-id]").parent(), !1)
                    }
                }, h = ".layui-layout-admin .site-mobile-shade";
            0 === e(h).length && e(".layui-layout-admin").append('<div class="site-mobile-shade"></div>'), e(h).click((function () {
                n.flexible(!0)
            })), l.pageTabs && 0 === e(s).length && (e(r).html(['<div class="layui-tab" lay-allowClose="true" lay-filter="', c, '" lay-autoRefresh="', "true" == l.tabAutoRefresh, '">', '   <ul class="layui-tab-title"></ul><div class="layui-tab-content"></div>', "</div>", '<div class="layui-icon admin-tabs-control layui-icon-prev" ew-event="leftPage"></div>', '<div class="layui-icon admin-tabs-control layui-icon-next" ew-event="rightPage"></div>', '<div class="layui-icon admin-tabs-control layui-icon-down">', '   <ul class="layui-nav" lay-filter="admin-pagetabs-nav">', '      <li class="layui-nav-item" lay-unselect>', '         <dl class="layui-nav-child layui-anim-fadein">', '            <dd ew-event="closeThisTabs" lay-unselect><a>关闭当前标签页</a></dd>', '            <dd ew-event="closeOtherTabs" lay-unselect><a>关闭其它标签页</a></dd>', '            <dd ew-event="closeAllTabs" lay-unselect><a>关闭全部标签页</a></dd>', "         </dl>", "      </li>", "   </ul>", "</div>"].join("")), t.render("nav", "admin-pagetabs-nav")), t.on("nav(admin-side-nav)", (function (a) {
                var i = e(a), t = i.attr("lay-href");
                if (t && "#" !== t) {
                    if (0 === t.indexOf("javascript:")) return new Function(t.substring(11))();
                    var n = i.attr("ew-title") || i.text().replace(/(^\s*)|(\s*$)/g, ""), l = i.attr("ew-end");
                    try {
                        l = l ? new Function(l) : void 0
                    } catch (a) {
                        console.error(a)
                    }
                    b.openTab({url: t, title: n, end: l}), layui.event.call(this, "admin", "side({*})", {href: t})
                }
            })), t.on("tab(" + c + ")", (function () {
                var a = e(this).attr("lay-id");
                b.mTabPosition = a !== b.homeUrl ? a : void 0, l.cacheTab && n.putTempData("tabPosition", b.mTabPosition), n.activeNav(a), n.rollPage("auto"), "true" != e(s).attr("lay-autoRefresh") || y || n.refresh(a, !0), y = !1, layui.event.call(this, "admin", "tab({*})", {layId: a})
            })), t.on("tabDelete(" + c + ")", (function (a) {
                var i = b.mTabList[a.index - 1];
                i && (b.mTabList.splice(a.index - 1, 1), l.cacheTab && n.putTempData("indexTabs", b.mTabList), m[i.menuPath] && m[i.menuPath].call(), layui.event.call(this, "admin", "tabDelete({*})", {layId: i.menuPath})), 0 === e(s + ">.layui-tab-title>li.layui-this").length && e(s + ">.layui-tab-title>li:last").trigger("click")
            })), e(document).off("click.navMore").on("click.navMore", "[nav-bind]", (function () {
                var a = e(this).attr("nav-bind");
                e('ul[lay-filter="admin-side-nav"]').addClass("layui-hide"), e('ul[nav-id="' + a + '"]').removeClass("layui-hide"), e(o + ">.layui-nav .layui-nav-item").removeClass("layui-this"), e(this).parent(".layui-nav-item").addClass("layui-this"), n.getPageWidth() <= 768 && n.flexible(!1), layui.event.call(this, "admin", "nav({*})", {navId: a})
            })), l.openTabCtxMenu && l.pageTabs && layui.use("contextMenu", (function () {
                layui.contextMenu && e(s + ">.layui-tab-title").off("contextmenu.tab").on("contextmenu.tab", "li", (function (a) {
                    var i = e(this).attr("lay-id");
                    return layui.contextMenu.show([{
                        icon: "layui-icon layui-icon-refresh",
                        name: "刷新当前",
                        click: function () {
                            t.tabChange(c, i), "true" != e(s).attr("lay-autoRefresh") && n.refresh(i)
                        }
                    }, {
                        icon: "layui-icon layui-icon-close-fill ctx-ic-lg", name: "关闭当前", click: function () {
                            n.closeThisTabs(i)
                        }
                    }, {
                        icon: "layui-icon layui-icon-unlink", name: "关闭其他", click: function () {
                            n.closeOtherTabs(i)
                        }
                    }, {
                        icon: "layui-icon layui-icon-close ctx-ic-lg", name: "关闭全部", click: function () {
                            n.closeAllTabs()
                        }
                    }], a.clientX, a.clientY), !1
                }))
            })), a("index", b)
        }))
    }
});