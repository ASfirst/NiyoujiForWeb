// JavaScript Document

$(document).ready(function () {
    Init.initLayout1Width();
    Init.initTravelnote();
    Controller.bindListener();
});

const Init = {
        initLayout1Width: function () {
            var usedWidth = $(window).width();
            //$("#layout1 #action_box").css("width", usedWidth + "px");
        }
        ,
        initTravelnote: function () {
            var travelnoteId = RequestParametersUtil.getParameter("travelnoteId");
            Model.travelnoteHandler.obtainingTravelnote(travelnoteId, function (receivedTravelnote) {
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
        $(document).scroll(function () {
            var y = document.body.scrollTop;
            $("#layout1 #action_box").css("top", y + "px");
            //alert("aa");
        });
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
};

const View = {
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

            //add page into the container element.
            $("#travelnote .container").append($newTravelnotePage);
        },
        hideTemplatePage: function () {
            $("#travelnote_page_template").hide();
        }

    }
};