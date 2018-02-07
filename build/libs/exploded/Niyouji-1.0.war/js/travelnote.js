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
                View.travelnoteView.setTitle(receivedTravelnote.travelnoteTitle);
                View.travelnoteView.setDate(receivedTravelnote.createTime);

                receivedTravelnote.travelnotePages.forEach(function (value) {
                    View.travelnoteView.addPage(value);
                });

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
            $("#travelnote .container").append($newTravelnotePage);
        }

    }
};