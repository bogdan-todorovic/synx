(function() {
    angular.module("synxApp")
        .config(["$stateProvider", function($stateProvider) {

            $stateProvider
                .state("newreport", {
                    url: "/newreport/{subforum}",
                    templateUrl: "report/newReportForm.html",
                    controller: "newReportController"
                });
            }
        ]);
})();
    