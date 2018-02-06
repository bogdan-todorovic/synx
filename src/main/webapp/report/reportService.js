(function() {
    angular.module("synxApp")
        .service("reportService", ["$http", function($http) {

            var self = this;
            self.baseUrl = "rest/reports";

            self.createReport = function(data) {
                return $http.post(self.baseUrl, data);
            };

            self.getReports = function() {
                return $http.get(self.baseUrl + "/admin");
            };

            self.updateReport = function(id, data) {
                return $http.put(self.baseUrl + "/" + id, data);
            };
            
        }]); 
})();