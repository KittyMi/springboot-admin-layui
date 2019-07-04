layui.toDateString = function(d, format){

    if(typeof d=='undefined'){
        return "";
    }
    if(d==''){
        return "";
    }
    var date = new Date(d)
        ,ymd = [
        this.digit(date.getFullYear(), 4)
        ,this.digit(date.getMonth() + 1)
        ,this.digit(date.getDate())
    ]
        ,hms = [
        this.digit(date.getHours())
        ,this.digit(date.getMinutes())
        ,this.digit(date.getSeconds())
    ];

    format = format || 'yyyy-MM-dd HH:mm:ss';

    return format.replace(/yyyy/g, ymd[0])
        .replace(/MM/g, ymd[1])
        .replace(/dd/g, ymd[2])
        .replace(/HH/g, hms[0])
        .replace(/mm/g, hms[1])
        .replace(/ss/g, hms[2]);
};

layui.toDateString = function(d, format){

    if(typeof d=='undefined'){
        return "";
    }
    if(d==''){
        return "";
    }
    var date = new Date(d)
        ,ymd = [
        this.digit(date.getFullYear(), 4)
        ,this.digit(date.getMonth() + 1)
        ,this.digit(date.getDate())
    ]
        ,hms = [
        this.digit(date.getHours())
        ,this.digit(date.getMinutes())
        ,this.digit(date.getSeconds())
    ];

    format = format || 'yyyy-MM-dd HH:mm:ss';

    return format.replace(/yyyy/g, ymd[0])
        .replace(/MM/g, ymd[1])
        .replace(/dd/g, ymd[2])
        .replace(/HH/g, hms[0])
        .replace(/mm/g, hms[1])
        .replace(/ss/g, hms[2]);
};

//数字前置补零
layui.digit = function(num, length, end){
    var str = '';
    num = String(num);
    length = length || 2;
    for(var i = num.length; i < length; i++){
        str += '0';
    }
    return num < Math.pow(10, length) ? str + (num|0) : num;
};
layui.link=function(name,path,id){
    return "<a  href='"+path+"' onclick='record("+id+");'>"+name+"</a>";
}

layui.state=function(s){
    var btn="";
    if(s==-1){
        btn="<a class='layui-btn layui-btn-xs layui-btn-danger'>已结束</a>";
    }else if(s==0){
        btn="<a class='layui-btn layui-btn-xs layui-btn-primary'>未开始</a>";
    }else if(s==1){
        btn="<a class='layui-btn layui-btn-xs layui-btn-normal'>进行中</a>";
    }
    return btn;
}

layui.role=function(role){
    var btn="";
    if(role.id==1){
        btn="<a class='layui-btn layui-btn-xs layui-btn-normal'>"+role.roleName +"</a>";
    }else if(role.id==2){
        btn="<a class='layui-btn layui-btn-xs '>"+role.roleName +"</a>";
    }else if(role.id==3){
        btn="<a class='layui-btn layui-btn-xs layui-btn-warm'>"+role.roleName +"</a>";
    }
    return btn;
}