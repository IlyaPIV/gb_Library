angular.module('employee-front').controller('genresController', function ($rootScope, $scope, $http, $location, $localStorage) {

    const corePath = 'http://' + window.location.hostname + ':8060/official/api/v1';

    $scope.editId = -10;

    $input1 = document.getElementById('input_title');
    $input2 = document.getElementById('input_author');
    $input3 = document.getElementById('input_genre');
    $input4 = document.getElementById('input_description');
    $input5 = document.getElementById('selectAge');


    $input1.onchange = function () {
        $scope.loadWorldBooks();
    };
    $input2.onchange = function () {
        $scope.loadWorldBooks();
    };

    $input3.onchange = function () {
        $scope.loadWorldBooks();
    };

    $input4.onchange = function () {
        $scope.loadWorldBooks();
    };
    $input5.onchange = function () {
        $scope.loadWorldBooks();
    };


    $scope.loadWorldBooks = function () {

        $http({
                url: corePath + '/worldBook',
                method: 'GET',
                params: {
                    title: document.getElementById("input_title").value,
                    genre: document.getElementById("input_genre").value,
                    author: document.getElementById("input_author").value,
                    description: document.getElementById("input_description").value,
                    ageRating: document.getElementById("selectAge").value
                }

            }
        ).then(function (response) {
            $scope.booksList = response.data;
            for (const responseElement of $scope.booksList) {
                responseElement.authorName = responseElement.author.firstName + " " + responseElement.author.lastName;

            }

        });
    };
    $scope.addBookToLibrary = function (id) {
        document.getElementById("booksList").style.display = 'none';
        document.getElementById("editForm").style.display = 'none';
        document.getElementById("addToLibraryForm").style.display = 'inline';
        $http({
                url: corePath + '/worldBook/' + id,
                method: 'GET'
            }
        ).then(function (response) {
            document.getElementById("book_name").innerText = response.data.title;
            document.getElementById("book_author").innerText = response.data.author.firstName + " " + response.data.author.lastName;
            document.getElementById("book_id").innerText = "(worldBookId=" + response.data.id + ")";
        });
    }

    $scope.addLibraryBook = function (id) {


    }


    $scope.goToEdit = function (id) {
        $scope.editId = id;
        if (id < 0) {
            document.getElementById("booksList").style.display = 'inline';
            document.getElementById("editForm").style.display = 'none';
            document.getElementById("addToLibraryForm").style.display = 'none';
            $scope.loadWorldBooks();
        } else {
            document.getElementById("booksList").style.display = 'none';
            document.getElementById("editForm").style.display = 'inline';
            document.getElementById("addToLibraryForm").style.display = 'none';

            $http({
                    url: corePath + '/authors/all',
                    method: 'GET'
                }
            ).then(function (response) {

                let selectForm = document.getElementById('selectAuthor');

                for (const responseElement of response.data) {
                    let opt = document.createElement('option');
                    opt.innerHTML = responseElement.firstName + " " + responseElement.lastName;
                    opt.value = responseElement.id;
                    selectForm.appendChild(opt);
                }
                // document.getElementById("selectAuthor").value = response.data.name;
                // document.getElementById("form_description").value = response.data.description;
            });
            $http({
                    url: corePath + '/genres',
                    method: 'GET'
                }
            ).then(function (response) {
                let selectForm = document.getElementById('selectGenre');

                for (const responseElement of response.data) {
                    let opt = document.createElement('option');
                    opt.innerHTML = responseElement.name;
                    opt.value = responseElement.id;
                    selectForm.appendChild(opt);
                }
                // document.getElementById("selectAuthor").value = response.data.name;
                // document.getElementById("form_description").value = response.data.description;
            });



            //
            // if (id === 0) {
            //     document.getElementById("genre_id").innerText = "Добавление новой книги:";
            // }
            //
            // if (id > 0) {
            //     document.getElementById("genre_id").innerText = "Редактирование id: " + id;
            //     $http({
            //             url: corePath + '/worldBook/' + id,
            //             method: 'GET'
            //         }
            //     ).then(function (response) {
            //         document.getElementById("form_genre").value = response.data.name;
            //         document.getElementById("form_description").value = response.data.description;
            //     });
            // }
        }

    }

    $scope.saveBook = function () {
        if ($scope.newBook == null) {
            if (($scope.editId === 0)) {
                alert('Форма не заполнена');
            }
            else {
                $scope.goToEdit(-10);
            }
            return;
        }
        if ($scope.editId === 0) {
            $scope.newBook.name = document.getElementById("edit_name").value;
            $scope.newBook.description = document.getElementById("edit_description").value;
            $scope.newBook.description = document.getElementById("edit_description").value;

            $http.post(corePath + '/worldBook', $scope.newBook)
                .then(function successCallback(response) {
                    $scope.newBook = null;
                    alert('Книга успешно добавлена');
                    $scope.goToEdit(-10);
                }, function failureCallback(response) {
                    console.log(response);
                    alert(response.data.message);
                }).catch(function (err) {
                console.log(err);
                if (err.status === 401) {
                    alert("Пожалуйста, авторизуйтесь");
                    $scope.clearUser();
                } else alert(err.data.message)
            });
        }
        // else {
        //     $scope.newBook.id = $scope.editId;
        //     $scope.newBook.name = document.getElementById("form_genre").value;
        //     $scope.newBook.description = document.getElementById("form_description").value;
        //     $http.put(corePath + '/worldBooks', $scope.newBook)
        //         .then(function successCallback(response) {
        //             $scope.newBook = null;
        //             alert('Жанр успешно сохранён');
        //             $scope.goToEdit(-10);
        //         }, function failureCallback(response) {
        //             console.log(response);
        //             alert(response.data.message);
        //         }).catch(function (err) {
        //         console.log(err);
        //         if (err.status === 401) {
        //             alert("Пожалуйста, авторизуйтесь");
        //             $scope.clearUser();
        //         } else alert(err.data.message)
        //     });
        //
        // }
    }



    $scope.loadWorldBooks();
});