<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="../libs/bootstrap-4.1.3/css/bootstrap.css">
    <link rel="stylesheet" href="../libs/jquery-3.3.1/jquery-3.3.1.js">

    <title>Страны</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style type="text/css">
        body {
            font-family: 'Ubuntu Condensed', sans-serif;
            font-size: large;
            background-image: url("../img/orig.jpeg");
            background-size: 139%;
        }

        .imagecontainer1 {
            width: 200px;
            height: 200px;
            margin: 30px;
            background-image: url("../img/turkey.jpg");
            display: inline-block;
        }

        .imagecontainer2 {
            width: 200px;
            height: 200px;
            margin: 30px;
            background-image: url("../img/cyprus.jpg");
            display: inline-block;
        }

        .imagecontainer3 {
            width: 200px;
            height: 200px;
            margin: 30px;
            background-image: url("../img/egypt.jpg");
            display: inline-block;
        }
    </style>
    <style type="text/css">
        tcxspan {
            text-decoration: underline;
            cursor: pointer;
        }
    </style>
</head>
<body>

<div class="container">
    <div>
        <script type="text/javascript">
            function changelang(lang_id) {
                document.getElementById('lang_id').value = lang_id;
                if (lang_id === "RU") {
                    document.getElementById('lang_en').className = "text-muted small";
                    document.getElementById('lang_ru').className = "text-primary";
                } else {
                    document.getElementById('lang_ru').className = "text-muted small";
                    document.getElementById('lang_en').className = "text-primary";
                }
                document.getElementById('lang_change').submit();
            }
        </script>
        <form style="float: right" name="lang_change" id="lang_change" method="post"
              action="http://localhost:8080/searchtour">
            <strong class="text-muted small" id="lang_en" onclick="changelang('EN')">EN</strong> | <strong
                class="text-primary " id="lang_ru" onclick="changelang('RU')">RU</strong>
            <input type="hidden" name="lang_id" id="lang_id" value="">
            <input type="hidden" name="command" value="language">
        </form>
        <table>
            <tbody>
            <tr>
                <td class="col-md-3">
                    <img src="../img/travel-logo.png" style="height: 300px;"/>
                </td>
                <td class="col-md-9" style="vertical-align: middle; display: table-cell; text-align: right">
                    <h1 style="color: #210474; font-weight: bold"> Туристическое агенство<br>Dream <br>Travel <br>Planning
                    </h1>
                </td>
            </tr>
            </tbody>
        </table>
        <div align="center">
            <a class="btn btn-primary" href="index.jsp" role="button">Home</a>
            <a class="btn btn-primary" href="../pages/countries.jsp" role="button">Countries</a>
            <a class="btn btn-primary" href="#" role="button">Hotels</a>
            <a class="btn btn-primary" href="hot-tours.jsp" role="button">Hot tours</a>
            <a class="btn btn-primary" href="search-tour.jsp" role="button">Search tour</a>
            <a class="btn btn-primary" href="#" role="button">Most popular tours</a>
        </div>
    </div>
    <table class="table">
        <tbody>
        <tr>
            <td class="col-md-2" style="vertical-align:top">
                <div class="wrapper" align="center">
                    <form action="" method="post" class="form-horizontal">
                        <div class="form-group " style="margin-bottom: 0">
                            <label for="email" class=" control-label">E-mail <span class="glyphicon glyphicon-asterisk"
                                                                                   aria-hidden="true"></span> </label>
                            <div class="col-sm-12">
                                <input class="form-control" id="email" name="email" type="text" value=""
                                       placeholder="email"/>
                            </div>
                        </div>
                        <div class="form-group " style="margin-bottom: 0">
                            <label for="password" class=" control-label">Password <span
                                    class="glyphicon glyphicon-asterisk" aria-hidden="true"></span> </label>
                            <div class="col-sm-12">
                                <input class="form-control" id="password" name="password" type="password" value=""
                                       placeholder="password"/>
                            </div>
                        </div>
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" name="cookieOn"> Запомнить меня
                            </label>
                        </div>
                        <button type="submit" class="btn btn-primary form-control" name="command" value="auth">Войти
                        </button>
                    </form>
                    <a href="register.jsp" class="small">Регистрация </a> | <a href="recovery.jsp" class="small">Забыли
                    пароль? </a>
                    <br/>
                    <br/>
                    <p><a href="search-tour.jsp" class="">Подбор туров</a></p>
                </div>
            </td>
            <div class="row">
                <td class="col-md-9">
                    <center>
                        <br>
                        <h1 align="row">Страны</h1>
                        <div class="imagecontainer1">Турция</div>
                        <div class="imagecontainer2">Кипр</div>
                        <div class="imagecontainer3">Египет</div>
                    </center>
                </td>
            </div>
        </tr>
        </tbody>
    </table>
    <div class="wrapper">
        <br>
        <h5 align="center">© Сергей Ивко</h5>
        <br>
    </div>
</div>
</body>
</html>