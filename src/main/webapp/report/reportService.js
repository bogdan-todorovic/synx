(function() {
    angular.module("synxApp")
        .service("reportService", ["$http", function($http) {

            var self = this;
            self.baseUrl = "rest/reports";

            self.createReport = function(data) {
                return $http.post(self.baseUrl, data);
            };

            
            
        }]); 
})();