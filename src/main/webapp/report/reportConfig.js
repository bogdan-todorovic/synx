(function() {
    angular.module("synxApp")
        .config(["$stateProvider", function($stateProvider) {

            $stateProvider
                .state("newreport", {
                    url: "/newreport/{subforum}",
                    templateUrl: "report/newReportForm.html",
                    controller: "newReportController"
                })
                
                .state("reports", {
                    url: "/reports",
                    templateUrl: "report/reports.html",
                    controller: "reportsController",
                    resolve: {
                        undecreedReports: function(reportService) {
                            return reportService.getReports()
                                .then(function(response) {
                                    return response.data;
                                });
                        }
                    }
                });
            }
        ]);
})();
    