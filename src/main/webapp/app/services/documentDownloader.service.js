angular
    .module('app')
    .service('DocumentDownloaderService', DocumentDownloaderService);

function DocumentDownloaderService($window, $cookies) {

    var self = {
        downloadDocument: downloadDocument
    };

    return self;

    function downloadDocument(baseUrl, type) {
        $window.open(baseUrl + '?type=' + type + '&access_token=' + $cookies.get('recourse-access-token'));
    }
}