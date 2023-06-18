angular.module('reader-front').controller('booksWishlistController', function ($scope, $http) {
    let contextPath = 'http://localhost:5555/reader/api/v1/wishlist';

    let defaultPage = 1;
    let currentPage = 1;

    const navigation = document.getElementById("navigation");
    const emptyWishlistPanel = document.getElementById("emptyWishlistPanel");
    const wishlistTable = document.getElementById("wishlistTable");


    $scope.generatePagesIndexes = function (totalPages) {
        let arr = [];
        for (let i = 0; i < totalPages; i++) {
            arr.push(i + 1);
        }
        $scope.pagesNav = arr;
    };

    $scope.loadBooksWishlist = function (pageIndex = defaultPage) {
        $http({
            url: contextPath,
            method: 'GET',
            params: {
                p: pageIndex
            }
        }).then(function (response) {
            currentPage = pageIndex;
            if (response.data.content.length === 0) {
                emptyWishlistPanel.style.display = 'inline';
                wishlistTable.style.display = 'none';
                navigation.style.display = 'none';
            } else {
                emptyWishlistPanel.style.display = 'none';
                wishlistTable.style.display = 'inline';
                navigation.style.display = 'inline';
            }
            $scope.booksWishlist = response.data;
            console.log($scope.booksWishlist);
            $scope.generatePagesIndexes($scope.booksWishlist.totalPages);
        });
    };

    $scope.removeCurrentBookFromWishlist = function (recordId) {
        $http.delete(contextPath + '/' + recordId).then(function () {
            $scope.loadBooksWishlist(currentPage);
        });
    };

    $scope.loadBooksWishlist();
});