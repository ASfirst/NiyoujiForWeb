function RequestParameters() {

    var requestParameters = {};
    requestParameters.parameters = [];
    var url = window.location.search; //获取url中含"?"符后的字串
    if (url.indexOf("?") !== -1) {

        var str = url.substr(1);

        strs = str.split("&");

        for (var i = 0; i < strs.length; i++) {
            var key = strs[i].split("=")[0];
            var value = decodeURI(strs[i].split("=")[1]);
            requestParameters.parameters[key] = value;
        }

    }

    requestParameters.getParameter = function (key) {
        return requestParameters.parameters[key];
    };

    return requestParameters;
}

const RequestParametersUtil = {
    getParameter: function (key) {
        var reg = new RegExp("(^|&)" + key + "=([^&]*)(&|$)", "i");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) {
            return decodeURI(r[2]);
        }
        return null;
    }

};