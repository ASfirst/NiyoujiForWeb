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
            Model.travelnoteHandler.obtainingTravelnote(travelnoteId);
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
        obtainingTravelnote: function (travelnoteId) {
            console.info(travelnoteId);
            if (travelnoteId == null) {
                travelnoteId = 1;
                console.info("arrive");
            }
            var url = "getTravelnote.do?travelnoteId=" + travelnoteId;
            $.get(url, function (data, status) {
                if (status === "success") {
                    this.travelnote = data;
                }
            });
        }
    }
};

const View = {};