(function() {
    angular.module("synxApp")
        .service("messageService", ["$http", function($http) {

            var self = this;
            self.baseUrl = "rest/messages";

            self.createMessage = function(data) {
                return $http.post(self.baseUrl, data);
            };
        }]); 
})();