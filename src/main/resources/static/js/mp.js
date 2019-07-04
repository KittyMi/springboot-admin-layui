/*后台首页Js*/
//操作对象
var App=new Object();
//用户登出
App.logout=function () {
    layer.confirm('您确定要退出系统吗？', {
        title:"登出提示",
        icon:3,
        skin: 'layui-layer-lan',
        btn: ['确定','我还要再想想'] //按钮
    }, function(){
        //var pathName=window.document.location.pathname;
        $.post(pathName+"/api/user/logout",{},function (res) {
            if(res.code==0){
                window.location.href=pathName+"/login";
            }
        },"JSON");
    }, function(){

    });
}
layui.use('element', function () {
    var $ = layui.jquery
        , element = layui.element;

    window.layIds=["0"];//定义全局变量来存在菜单的Id
    //触发事件
    var active = {
        tabAdd: function (obj) {
            var title=$(obj).html(),layId=$(obj).attr('lay-id'),url=$(obj).attr("data-url");
            if(layId==-1){
                element.tabChange('menu', -1);
                return;
            }
            if(typeof url=='undefined'){
                layer.msg("功能暂未开放,敬请期待下一波的升级吧！",{
                    icon:6
                });
                return;
            }
            if($.inArray(layId,window.layIds)!=-1){
                element.tabChange("menu", layId);
            }else {
                layIds.push(layId);
                //新增一个Tab项
                element.tabAdd('menu', {
                    title: title,//tab标题,
                    content: "<iframe id='content"+layId+"' frameborder='0' width='100%' scrolling='auto' src='"+pathName+url+"'></iframe>",
                    id: layId,
                    url:url
                });
                //增加点击关闭事件
                var r = $(".layui-tab-title").find("li");
                //每次新打开tab都是最后一个，所以只对最后一个tab添加点击关闭事件
                r.eq(r.length - 1).children("i").on("click", function () {
                    element.tabDelete("menu", $(this).parent("li").attr('lay-id'));
                }), element.tabChange("menu", layId);
                resize();
                element.init();
            }

        }
    };
    //删除监听
    element.on('tabDelete(menu)', function(data){
        var layId=$(this).attr('lay-id');
        window.layIds.splice($.inArray(layId,window.layIds),1);//删除

    });
    /*注册点击事件*/
    $('.layui-nav-item a').on('click', function () {
        var othis = $(this), type = othis.data('type');
        active[type] ? active[type].call(this, othis) : '';
    });

    //iframe自适应
    function resize(){
        var $content = $('.layui-tab-content');
        $content.height($(this).height() - 147);
        $content.find('iframe').each(function() {
            $(this).height($content.height());
        });
    }
    $(window).on('resize', function() {
        var $content = $('.layui-tab-content');
        $content.height($(this).height() - 147);
        $content.find('iframe').each(function() {
            $(this).height($content.height());
        });
    }).resize();

    /*//Hash地址的定位
    var layId = location.hash.replace(/^#menuId=/, '');
    element.tabChange('menu', layId);
    element.on('tab(menu)', function (elem) {
        location.hash = 'menuId=' + $(this).attr('lay-id');
    });*/
    /*菜单展开关闭*/
    $('.layout-side-toggle').on('click', function() {
        var sideWidth = $('.layui-side').width();
        if(sideWidth === 220) {
            $('.layui-body').animate({
                left: '0'
            });
            $('.layui-footer').animate({
                left: '0'
            });
            $('.layui-side').animate({
                width: '0'
            });
            $('.layui-logo').animate({
                width: '0'
            });
            $('.layui-header').animate({
                left: '0'
            });



        } else {
            $('.layui-body').animate({
                left: '220px'
            });
            $('.layui-footer').animate({
                left: '220px'
            });
            $('.layui-side').animate({
                width: '220px'
            });
            $('.layui-logo').animate({
                width: '220px'
            });
            $('.layui-header').animate({
                left: '220px'
            });

        }
    });
    $("#homePage").find("i.layui-tab-close").hide();

    //绑定右键菜单
    $.contextMenu({
        selector: '.layui-tab-title li:not(:first-child)',
        callback: function(key, options) {
            /*刷新*/
            if(key=='refresh'){
                if(typeof $(this).attr("lay-id")=='undefined'){
                    return;
                }
                document.getElementById('content'+$(this).attr("lay-id")).contentWindow.location.reload(true);
            }else if(key=='deleteThis'){
                element.tabDelete('menu', $(this).attr("lay-id")).init();
            }else if((key=='deleteAll')) {
                $(window.layIds).each(function (i, layId) {
                    element.tabDelete('menu', layId);
                })
            }else if(key=='newBlank'){
                window.open(pathName+$(this).attr("lay-url"))
            }
        },
        items: {
            "deleteThis": {name: "关闭当前",icon:"fa-times green"},
            "deleteAll": {name: "关闭所有",icon:"fa-trash-o green"},
            "newBlank": {name: "重新打开",icon:"fa-television green"},
            "sep1": "---------",
            "refresh": {name: "刷新当前",icon:"fa-refresh green"}
        }
    });



    //关闭其他
    $(".closePageOther").on("click",function(){
        if($("#top_tabs li").length>2 && $("#top_tabs li.layui-this cite").text()!="后台首页"){
            var menu = JSON.parse(window.sessionStorage.getItem("menu"));
            $("#top_tabs li").each(function(){
                if($(this).attr("lay-id") != '' && !$(this).hasClass("layui-this")){
                    element.tabDelete("bodyTab",$(this).attr("lay-id")).init();
                    //此处将当前窗口重新获取放入session，避免一个个删除来回循环造成的不必要工作量
                    for(var i=0;i<menu.length;i++){
                        if($("#top_tabs li.layui-this cite").text() == menu[i].title){
                            menu.splice(0,menu.length,menu[i]);
                            window.sessionStorage.setItem("menu",JSON.stringify(menu));
                        }
                    }
                }
            })
        }else if($("#top_tabs li.layui-this cite").text()=="后台首页" && $("#top_tabs li").length>1){
            $("#top_tabs li").each(function(){
                if($(this).attr("lay-id") != '' && !$(this).hasClass("layui-this")){
                    element.tabDelete("bodyTab",$(this).attr("lay-id")).init();
                    window.sessionStorage.removeItem("menu");
                    menu = [];
                    window.sessionStorage.removeItem("curmenu");
                }
            })
        }else{
            layer.msg("没有可以关闭的窗口了@_@");
        }
        //渲染顶部窗口
        tab.tabMove();
    })
    //关闭全部
    $(".closePageAll").on("click",function(){
        if($("#top_tabs li").length > 1){
            $("#top_tabs li").each(function(){
                if($(this).attr("lay-id") != ''){
                    element.tabDelete("bodyTab",$(this).attr("lay-id")).init();
                    window.sessionStorage.removeItem("menu");
                    menu = [];
                    window.sessionStorage.removeItem("curmenu");
                }
            })
        }else{
            layer.msg("没有可以关闭的窗口了@_@");
        }
        //渲染顶部窗口
        tab.tabMove();
    });


    //锁屏
    function lockPage(){
        layer.open({
            title : false,
            type : 1,
            content : $("#lock-box"),
            closeBtn : 0,
            shade : 0.9
        })
    }
    $(".lockcms").on("click",function(){
        window.sessionStorage.setItem("lockcms",true);
        lockPage();
    })
    // 判断是否显示锁屏
    if(window.sessionStorage.getItem("lockcms") == "true"){
        lockPage();
    }
    // 解锁
    $("#unlock").on("click",function(){
        if($(this).siblings(".admin-header-lock-input").val() == ''){
            layer.msg("请输入解锁密码！");
        }else{
            if($(this).siblings(".admin-header-lock-input").val() == "123456"){
                window.sessionStorage.setItem("lockcms",false);
                $(this).siblings(".admin-header-lock-input").val('');
                layer.closeAll("page");
            }else{
                layer.msg("密码错误，请重新输入！");
            }
        }
    });
    $(document).on('keydown', function() {
        if(event.keyCode == 13) {
            $("#unlock").click();
        }
    });

});