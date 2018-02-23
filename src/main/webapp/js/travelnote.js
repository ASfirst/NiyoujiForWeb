// JavaScript Document

$(document).ready(function () {
    Init.initLayout1Width();
    Init.initAll();
    Controller.bindListener();
});

const Init = {
        initLayout1Width: function () {
            var usedWidth = $(window).width();
            $("#layout1").css("width", usedWidth + "px");
        }
        ,
        initAll: function () {
            var travelnoteId = RequestParametersUtil.getParameter("travelnoteId");
            Model.travelnoteHandler.obtainingTravelnote(travelnoteId, function (receivedTravelnote) {

                //obtain the NiyoujiUser datasource.
                var performerId = receivedTravelnote.performerId;
                Model.niyoujiUserHandler.obtainingNiyoujiUser(performerId, function (niyoujiUser) {
                    View.actionBox.setPerformerNickname(niyoujiUser.nickname);
                    View.actionBox.setPerformerSurfaceImage(niyoujiUser.surfaceImageUrl);
                });

                View.travelnoteView.setTitle(receivedTravelnote.travelnoteTitle);
                View.travelnoteView.setDate(receivedTravelnote.createTime);
                View.travelnoteView.setLocation(receivedTravelnote.location);

                receivedTravelnote.travelnotePages.forEach(function (value) {
                    View.travelnoteView.addPage(value);
                });
                View.travelnoteView.hideTemplatePage();

                //view to appraises
                receivedTravelnote.appraises.forEach(function (value) {
                    View.appraiseArea.addAppraise(value);
                });
                View.appraiseArea.hideAppraiseTemplate();
            });
        }

    }
;

const Controller = {
    bindListener: function () {
        /*$("#share_button").click(function () {
            View.appraiseArea.addAppraise({
                appraiseId: "2",
                travelnoteId: "1",
                nickname: "测试用户1",
                content: "克隆弹幕1",
                createTime: "2018-01-31 12:36:26.0"
            });
            View.appraiseArea.focusToHere();
        });*/
    }
};

const Model = {
    travelnoteHandler: {
        travelnote: null,
        obtainingTravelnote: function (travelnoteId, listener) {
            if (travelnoteId == null) {
                travelnoteId = 1;
            }
            var url = "getTravelnote.do?travelnoteId=" + travelnoteId;
            $.get(url, function (data, status) {
                if (status === "success") {
                    Model.travelnoteHandler.travelnote = data;
                    //console.info(Model.travelnoteHandler.travelnote);
                    listener(Model.travelnoteHandler.travelnote);
                }
            });
        }
        ,
        replaceJtEmoji: function (text) {
            var pattern = new RegExp("\\[[a-z][0-9]+\\]", "g");
            var jtMojiTemlate1 = "<span><img src='images/jtEmoji/";
            var jtMojiTemlate2 = ".png' width='18' height='18' alt=''/></span>";
            var newText = text;
            for (; ;) {
                var result = pattern.exec(text);
                if (result != null) {
                    var a = result[0].substring(1, result[0].length - 1);
                    var jtMojiTemlate = jtMojiTemlate1 + a + jtMojiTemlate2;
                    newText = newText.replace(result[0], jtMojiTemlate);
                    //console.info(newText);
                    //console.info(result[0] + ":" + result.index);
                }
                else {
                    break;
                }
            }
            return newText;
        }
    }
    ,
    niyoujiUserHandler: {
        obtainingNiyoujiUser: function (userId, listener) {
            if (userId == null) {
                return;
            }
            var url = "getNiyoujiUser.do?userId=" + userId;
            $.get(url, function (data, status) {
                if (status === "success") {
                    listener(data);
                }
            });
        }
    }
};

const View = {
    actionBox: {
        setPerformerNickname: function (nickname) {
            $("#action_box").find("#performer_nickname").html(nickname);
        }
        ,
        setPerformerSurfaceImage: function (surfaceImageUrl) {
            $("#action_box").find("#performer_surface_image").attr("src", surfaceImageUrl);
        }
    },
    travelnoteView: {
        setTitle: function (title) {
            $("#travelnote #travelnote_title").html(title);
        },
        setDate: function (createTime) {
            $("#travelnote #travelnote_date").html(createTime.substring(0, 10));
        },
        setLocation: function (location) {
            if (location == null) {
                $("#travelnote #travelnote_location").hide();
            } else {
                $("#travelnote #travelnote_location").html(location);
            }
        },
        addPage: function (travelnotePage) {
            var $newTravelnotePage = $("#travelnote_page_template").clone();

            //set text content with time.
            var textContent =
                (travelnotePage.textContent == null ? "" : travelnotePage.textContent + "<br>")
                + travelnotePage.createTime.substring(10, 16);

            //set the jtEmojis of text content
            textContent = Model.travelnoteHandler.replaceJtEmoji(textContent);

            $newTravelnotePage.find(".page_content").html(textContent);

            //set resource
            if (travelnotePage.pageType === "picture_and_word") {
                $newTravelnotePage.find(".image picture source")
                    .attr("srcset", travelnotePage.resourceUrl);
                $newTravelnotePage.find(".image picture img")
                    .attr("src", travelnotePage.resourceUrl);

                $newTravelnotePage.find(".video").remove();
            }
            else {
                var usedWidth = $(window).width();
                var $newTravelnotePageVideoView = $newTravelnotePage.find(".video video");
                $newTravelnotePageVideoView.attr("width", usedWidth);
                $newTravelnotePageVideoView.attr("src", travelnotePage.resourceUrl);
                $newTravelnotePage.find(".image").remove();
                $newTravelnotePage.click(function () {
                    $newTravelnotePage.find(".play_reminder").show();
                    setTimeout(function () {
                        $newTravelnotePage.find(".play_reminder").hide();
                    }, 1000);
                });
            }

            //set theme
            if (travelnotePage.pageType === "picture_and_word") {
                $newTravelnotePage.removeClass();
                $newTravelnotePage.addClass("row travelnote_page page_theme"
                    + travelnotePage.themePosition);
                $newTravelnotePage.find(".image .frame").attr("src", "images/pwthemes/"
                    + travelnotePage.themePosition + "/frame.png");
                var $travelnotePageTextElement = $newTravelnotePage.find(".page_content");
                $travelnotePageTextElement.removeClass();
                $travelnotePageTextElement.addClass("col-12 page_content page_theme"
                    + travelnotePage.themePosition);
            }

            //add barrages
            var delayTime = 0;
            travelnotePage.barrages.forEach(function (value) {
                setTimeout(function () {
                    View.barrageView.addBarrage($newTravelnotePage, value);
                }, delayTime);
                delayTime = delayTime + 3000;
            });

            //add page into the container element.
            $("#travelnote .container").append($newTravelnotePage);
        },
        hideTemplatePage: function () {
            $("#travelnote_page_template").hide();
        }

    },
    barrageView: {
        addBarrage: function ($newTravelnotePage, barrage) {
            var $trajectory = $newTravelnotePage.find(".trajectory");

            var $newBarrage = $("<div><span class='nickname'>Jt</span><span class='content'>hehe</span></div>");
            if (barrage.isPerformers) {
                $newBarrage.addClass("barrage2");
                $newBarrage.find(".nickname").html("主播 : ");
            }
            else {
                $newBarrage.addClass("barrage1");
                $newBarrage.find(".nickname").html(barrage.nickname + " : ");
            }

            $newBarrage.find(".content").html(barrage.content);
            $trajectory.append($newBarrage);

            this.displayBarrage($newBarrage);
        }
        ,
        displayBarrage: function ($barrage) {
            $barrage.css("right", "-50%");
            $barrage.animate({right: '120%'}, 12000, function () {
                View.barrageView.displayBarrage($barrage);
            });
        }
    }
    ,
    appraiseArea: {
        addAppraise: function (appraise) {
            var $newAppraise = $("#appraise_area").find("#appraise_template").clone();
            $newAppraise.show();
            $newAppraise.attr("id", "");
            $newAppraise.find(".appraise_nickname").html(appraise.nickname);
            $newAppraise.find(".appraise_content").html(appraise.content);
            $newAppraise.find(".appraise_time").html(appraise.createTime.substring(0, 16));

            $("#appraise_area").append($newAppraise);
        },
        hideAppraiseTemplate: function () {
            $("#appraise_area").find("#appraise_template").hide();
        },
        focusToHere: function () {
            location.href = "#appraise_area";
        }
    }
};