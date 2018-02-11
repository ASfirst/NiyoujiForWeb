// JavaScript Document

$(document).ready(function () {
    Init.initLayout1Width();
    Init.initTravelnote();
    Controller.bindListener();
});

const Init = {
        initLayout1Width: function () {
            var usedWidth = $(window).width();
            $("#layout1").css("width", usedWidth + "px");
        }
        ,
        initTravelnote: function () {
            var travelnoteId = RequestParametersUtil.getParameter("travelnoteId");
            Model.travelnoteHandler.obtainingTravelnote(travelnoteId, function (receivedTravelnote) {

                var performerId = receivedTravelnote.performerId;
                Model.niyoujiUserHandler.obtainingNiyoujiUser(performerId, function (niyoujiUser) {
                    View.actionBox.setPerformerNickname(niyoujiUser.nickname);
                    View.actionBox.setPerformerSurfaceImage(niyoujiUser.surfaceImageUrl);
                });

                View.travelnoteView.setTitle(receivedTravelnote.travelnoteTitle);
                View.travelnoteView.setDate(receivedTravelnote.createTime);

                receivedTravelnote.travelnotePages.forEach(function (value) {
                    View.travelnoteView.addPage(value);
                });
                View.travelnoteView.hideTemplatePage();

            });
        }

    }
;

const Controller = {
    bindListener: function () {
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
        addPage: function (travelnotePage) {
            var $newTravelnotePage = $("#travelnote_page_template").clone();

            //set text content and time.
            var textContent = travelnotePage.createTime.substring(10, 16)
                + (travelnotePage.textContent == null ? "" : "<br>" + travelnotePage.textContent);
            $newTravelnotePage.find(".page_content").html(textContent);

            //set resource
            if (travelnotePage.pageType === "picture_and_word") {
                $newTravelnotePage.find(".image picture source")
                    .attr("srcset", travelnotePage.resourceUrl);
                $newTravelnotePage.find(".image picture img")
                    .attr("src", travelnotePage.resourceUrl);

                $newTravelnotePage.find(".video").hide();
            }
            else {
                var usedWidth = $(window).width();
                $newTravelnotePage.find(".video video").attr("width", usedWidth);
                $newTravelnotePage.find(".video video").attr("src", travelnotePage.resourceUrl);
                $newTravelnotePage.find(".image").hide();
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
};