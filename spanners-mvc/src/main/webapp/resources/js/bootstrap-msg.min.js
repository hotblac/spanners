/**
 * Bootstrap Message (bootstrap.msg) plugin v0.3
 * Copyright (c) 2014 Duc Doan Hoang Minh
 *
 * @license https://github.com/bobkhin/bootstrap.msg/blob/master/LICENSE
 *
 * Date: Wed, May 14th, 2014 (GTM+7)
 */
!function(i,n){var s,e=n.Msg={iconMode:"bs",timeout:{danger:1e4,success:3e3,info:5e3,warning:5e3},info:function(i,n){this.show(i,"info",n)},error:function(i,n){this.danger(i,n)},danger:function(i,n){this.show(i,"danger",n)},success:function(i,n){this.show(i,"success",n)},warning:function(i,n){this.show(i,"warning",n)},show:function(n,o,a){var c=this,t=i("#msg"),r=!1;t[0]||(r=!0,t=i('<div id="msg"><a href="#" data-dismiss="msg" class="close">&times;</a><i></i> <span></span></div>'),t.find('[data-dismiss="msg"]').on("click",function(i){i.preventDefault(),c.hide()}),t.appendTo(document.body));var d="";switch(o){case"info":d="bs"===c.iconMode?"glyphicon glyphicon-info-sign":"fa fa-info-circle";break;case"danger":d="bs"===c.iconMode?"glyphicon glyphicon-remove-sign":"fa fa-times-circle";break;case"success":d="bs"===c.iconMode?"glyphicon glyphicon-ok-sign":"fa fa-check-circle";break;case"warning":d="bs"===c.iconMode?"glyphicon glyphicon-warning-sign":"fa fa-exclamation-triangle"}clearTimeout(s),s=null,t.find("span").html(n),t.find("i").attr("class",d),r?setTimeout(function(){t.attr("class","alert alert-"+o+" showed")},50):t.attr("class","alert alert-"+o+" showed"),void 0===a&&(a=e.timeout[o]),a>0&&(s=setTimeout(function(){c.hide()},a))},hide:function(){i("#msg").removeClass("showed")}}}(jQuery,window);