<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>SweetBloom Bakery - Menu</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Playfair+Display:wght@700&display=swap" rel="stylesheet">
    <style>
        /* Combine all styles here or link to an external stylesheet */
        .menu-container {
            display: flex;
            flex-direction: column;
            align-items: center;
            padding: 20px;
            background-color: #FFF3E6;
        }
        .menu-title {
            font-family: 'Playfair Display', serif;
            font-size: 2.5rem;
            color: #4A2C2A;
            margin-bottom: 2rem;
        }
        .menu-items {
            display: flex;
            flex-wrap: wrap;
            justify-content: space-around;
            gap: 20px;
            margin-bottom: 3rem;
        }
        .menu-item {
            background: #fff;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 10px 30px rgba(74, 44, 42, 0.1);
            width: 200px;
            text-align: center;
            transition: transform 0.3s ease, box-shadow 0.3s ease;
        }
        .menu-item:hover {
            transform: translateY(-10px);
            box-shadow: 0 20px 40px rgba(74, 44, 42, 0.2);
        }
        .menu-item img {
            max-width: 100%;
            border-radius: 10px;
        }
        .menu-item h3 {
            margin: 15px 0;
            color: #4A2C2A;
        }
        .menu-item p {
            color: #333;
            font-size: 1rem;
            margin-bottom: 10px;
        }
        .menu-item .price {
            font-weight: bold;
            color: #FF6B6B;
        }
        .order-btn {
            background: #FF6B6B;
            color: white;
            border: none;
            padding: 10px 20px;
            border-radius: 25px;
            font-weight: 600;
            cursor: pointer;
            transition: background 0.3s ease;
        }
        .order-btn:hover {
            background: #FF8E8E;
        }
    </style>
</head>
<body>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>SweetBloom Bakery - Menu</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Playfair+Display:wght@700&display=swap" rel="stylesheet">
    <style>
        .menu-container {
            display: flex;
            flex-direction: column;
            align-items: center;
            padding: 20px;
            background-color: #FFF3E6;
        }
        .menu-title {
            font-family: 'Playfair Display', serif;
            font-size: 2.5rem;
            color: #4A2C2A;
            margin-bottom: 2rem;
        }
        .menu-items {
            display: flex;
            flex-wrap: wrap;
            justify-content: space-around;
            gap: 20px;
            margin-bottom: 3rem;
        }
        .menu-item {
            background: #fff;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 10px 30px rgba(74, 44, 42, 0.1);
            width: 200px;
            text-align: center;
            transition: transform 0.3s ease, box-shadow 0.3s ease;
        }
        .menu-item:hover {
            transform: translateY(-10px);
            box-shadow: 0 20px 40px rgba(74, 44, 42, 0.2);
        }
        .menu-item img {
            max-width: 100%;
            border-radius: 10px;
        }
        .menu-item h3 {
            margin: 15px 0;
            color: #4A2C2A;
        }
        .menu-item p {
            color: #333;
            font-size: 1rem;
            margin-bottom: 10px;
        }
        .menu-item .price {
            font-weight: bold;
            color: #FF6B6B;
        }
        .order-btn {
            background: #FF6B6B;
            color: white;
            border: none;
            padding: 10px 20px;
            border-radius: 25px;
            font-weight: 600;
            cursor: pointer;
            transition: background 0.3s ease;
        }
        .order-btn:hover {
            background: #FF8E8E;
        }
    </style>
</head>
<body>
<div class="menu-container">
    <h1 class="menu-title">Our Cakes</h1>

    <div class="menu-items">
        <c:forEach var="cake" items="${cakes}">
            <div class="menu-item">
                <img src="${pageContext.request.contextPath}/images/${cake.image}" alt="${cake.name}">
                <h3>${cake.name}</h3>
                <p>${cake.description}</p>
                <p class="price">${cake.price}</p>
                <button class="order-btn">Order Now</button>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>

