(function() {
    angular.module("synxApp")
        .controller("reportsController", ["$scope", "$rootScope", "reportService", "undecreedReports", function($scope, $rootScope, reportService, undecreedReports) {
            
            $scope.reports = undecreedReports;

            $scope.accept = function(report) {
                report.decree = "ACCEPTED";
                update(report);
            };

            $scope.warn = function(report) {
                report.decree = "WARNING";
                update(report);
            };

            $scope.reject = function(report) {
                report.decree = "REJECTED";
                update(report);
            };

            function update(report) {
                reportService.updateReport(report.id, report)
                    .then(function(response) {
                        for (var i = 0; i < $scope.reports.length; i++)
                            if ($scope.reports[i].id === report.id) {
                                $scope.reports.splice(i,1);
                                break;
                            }
                    });
            }
        }]);
})();