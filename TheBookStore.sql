USE [master]
GO
/****** Object:  Database [TheBookStore]    Script Date: 14/07/2021 6:34:42 CH ******/
CREATE DATABASE [TheBookStore]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'TheBookStore', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.SQLEXPRESS\MSSQL\DATA\TheBookStore.mdf' , SIZE = 3264KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'TheBookStore_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.SQLEXPRESS\MSSQL\DATA\TheBookStore_log.ldf' , SIZE = 832KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [TheBookStore] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [TheBookStore].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [TheBookStore] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [TheBookStore] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [TheBookStore] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [TheBookStore] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [TheBookStore] SET ARITHABORT OFF 
GO
ALTER DATABASE [TheBookStore] SET AUTO_CLOSE ON 
GO
ALTER DATABASE [TheBookStore] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [TheBookStore] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [TheBookStore] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [TheBookStore] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [TheBookStore] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [TheBookStore] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [TheBookStore] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [TheBookStore] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [TheBookStore] SET  ENABLE_BROKER 
GO
ALTER DATABASE [TheBookStore] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [TheBookStore] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [TheBookStore] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [TheBookStore] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [TheBookStore] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [TheBookStore] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [TheBookStore] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [TheBookStore] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [TheBookStore] SET  MULTI_USER 
GO
ALTER DATABASE [TheBookStore] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [TheBookStore] SET DB_CHAINING OFF 
GO
ALTER DATABASE [TheBookStore] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [TheBookStore] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [TheBookStore] SET DELAYED_DURABILITY = DISABLED 
GO
USE [TheBookStore]
GO
/****** Object:  Table [dbo].[tblBook]    Script Date: 14/07/2021 6:34:42 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tblBook](
	[BookID] [int] IDENTITY(1,1) NOT NULL,
	[BookName] [nvarchar](50) NULL,
	[image] [varchar](max) NULL,
	[Price] [float] NULL,
	[quantity] [int] NULL,
	[Author] [varchar](50) NULL,
	[CurrentDate] [date] NULL,
	[Status] [bit] NULL,
	[CategoryID] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[BookID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tblCategory]    Script Date: 14/07/2021 6:34:42 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tblCategory](
	[CategoryID] [int] IDENTITY(1,1) NOT NULL,
	[CategoryName] [varchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[CategoryID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tblDetail]    Script Date: 14/07/2021 6:34:42 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblDetail](
	[DetailID] [int] IDENTITY(1,1) NOT NULL,
	[Price] [float] NULL,
	[quantity] [int] NULL,
	[OrderID] [int] NULL,
	[BookID] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[DetailID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[tblOrder]    Script Date: 14/07/2021 6:34:42 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblOrder](
	[OrderID] [int] IDENTITY(1,1) NOT NULL,
	[DateOrder] [date] NULL,
	[total] [float] NULL,
	[UserID] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[OrderID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[tblRoles]    Script Date: 14/07/2021 6:34:42 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tblRoles](
	[RoleID] [varchar](50) NOT NULL,
	[RoleName] [varchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[RoleID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tblUsers]    Script Date: 14/07/2021 6:34:42 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tblUsers](
	[UserID] [int] IDENTITY(1,1) NOT NULL,
	[UserName] [nvarchar](50) NOT NULL,
	[PassWord] [varchar](50) NOT NULL,
	[RoleID] [varchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[UserID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
SET IDENTITY_INSERT [dbo].[tblBook] ON 

INSERT [dbo].[tblBook] ([BookID], [BookName], [image], [Price], [quantity], [Author], [CurrentDate], [Status], [CategoryID]) VALUES (1, N'Dac Nhan Tam', N'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTQNPlgEoOc9ewlnANo9tIoX0QBkZSrzq0U2Q&usqp=CAU', 1000, 19, N' Dale Carnegie', CAST(N'2021-07-11' AS Date), 1, 4)
INSERT [dbo].[tblBook] ([BookID], [BookName], [image], [Price], [quantity], [Author], [CurrentDate], [Status], [CategoryID]) VALUES (2, N'The Gioi Dong Vat', N'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSngm40vp6ea-vwQgwptfHyvCs26hki60HY9g&usqp=CAU', 2000, 6, N'Ngo Minh Van', CAST(N'2021-07-11' AS Date), 1, 1)
INSERT [dbo].[tblBook] ([BookID], [BookName], [image], [Price], [quantity], [Author], [CurrentDate], [Status], [CategoryID]) VALUES (3, N'300 Bai Code Thieu Nhi', N'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRd6SjsDoilFAlrNbunVg1LShb1Hnc2b6FA1pPJCoFueYWqd9_C1dJuWtiEhFlmbzB3bt8&usqp=CAU', 5500, 9, N'baicodethieunhi ', CAST(N'2021-07-11' AS Date), 1, 3)
INSERT [dbo].[tblBook] ([BookID], [BookName], [image], [Price], [quantity], [Author], [CurrentDate], [Status], [CategoryID]) VALUES (4, N'Nha Gia Kim', N'https://sachxua.vn/wp-content/uploads/2020/01/sach-nha-gia-kim.jpg', 15000, 9, N'Paulo coelho', CAST(N'2021-07-11' AS Date), 1, 2)
INSERT [dbo].[tblBook] ([BookID], [BookName], [image], [Price], [quantity], [Author], [CurrentDate], [Status], [CategoryID]) VALUES (5, N'That Hinh Dai Toi', N'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSe6xJnR-4fjfX_Shz_1R57wQBFFXomSwofyA&usqp=CAU', 40000, 59, N'Kuro Ichihiro', CAST(N'2021-07-12' AS Date), 1, 2)
INSERT [dbo].[tblBook] ([BookID], [BookName], [image], [Price], [quantity], [Author], [CurrentDate], [Status], [CategoryID]) VALUES (6, N'One Piece Vol2', N'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRSm61cfIvgjctICIZwxtO4DYihkK7SRC9AEA&usqp=CAU', 4000, 17, N'Oda', CAST(N'2021-07-12' AS Date), 1, 2)
SET IDENTITY_INSERT [dbo].[tblBook] OFF
SET IDENTITY_INSERT [dbo].[tblCategory] ON 

INSERT [dbo].[tblCategory] ([CategoryID], [CategoryName]) VALUES (1, N'NXB Tre')
INSERT [dbo].[tblCategory] ([CategoryID], [CategoryName]) VALUES (2, N'NXB Kim Dong')
INSERT [dbo].[tblCategory] ([CategoryID], [CategoryName]) VALUES (3, N'MCBOOKS')
INSERT [dbo].[tblCategory] ([CategoryID], [CategoryName]) VALUES (4, N'NXB Quoc Te')
SET IDENTITY_INSERT [dbo].[tblCategory] OFF
SET IDENTITY_INSERT [dbo].[tblDetail] ON 

INSERT [dbo].[tblDetail] ([DetailID], [Price], [quantity], [OrderID], [BookID]) VALUES (1, 1000, 1, 1, 1)
INSERT [dbo].[tblDetail] ([DetailID], [Price], [quantity], [OrderID], [BookID]) VALUES (2, 5500, 1, 2, 3)
INSERT [dbo].[tblDetail] ([DetailID], [Price], [quantity], [OrderID], [BookID]) VALUES (3, 2000, 1, 3, 2)
INSERT [dbo].[tblDetail] ([DetailID], [Price], [quantity], [OrderID], [BookID]) VALUES (4, 5500, 1, 4, 3)
INSERT [dbo].[tblDetail] ([DetailID], [Price], [quantity], [OrderID], [BookID]) VALUES (5, 1000, 1, 5, 1)
INSERT [dbo].[tblDetail] ([DetailID], [Price], [quantity], [OrderID], [BookID]) VALUES (6, 4000, 1, 6, 6)
INSERT [dbo].[tblDetail] ([DetailID], [Price], [quantity], [OrderID], [BookID]) VALUES (7, 1000, 1, 7, 1)
INSERT [dbo].[tblDetail] ([DetailID], [Price], [quantity], [OrderID], [BookID]) VALUES (8, 2000, 1, 8, 2)
INSERT [dbo].[tblDetail] ([DetailID], [Price], [quantity], [OrderID], [BookID]) VALUES (9, 1000, 1, 9, 1)
INSERT [dbo].[tblDetail] ([DetailID], [Price], [quantity], [OrderID], [BookID]) VALUES (10, 1000, 1, 10, 1)
INSERT [dbo].[tblDetail] ([DetailID], [Price], [quantity], [OrderID], [BookID]) VALUES (11, 40000, 1, 10, 5)
INSERT [dbo].[tblDetail] ([DetailID], [Price], [quantity], [OrderID], [BookID]) VALUES (12, 5500, 1, 10, 3)
INSERT [dbo].[tblDetail] ([DetailID], [Price], [quantity], [OrderID], [BookID]) VALUES (13, 4000, 1, 10, 6)
INSERT [dbo].[tblDetail] ([DetailID], [Price], [quantity], [OrderID], [BookID]) VALUES (14, 1000, 1, 11, 1)
INSERT [dbo].[tblDetail] ([DetailID], [Price], [quantity], [OrderID], [BookID]) VALUES (15, 2000, 1, 12, 2)
INSERT [dbo].[tblDetail] ([DetailID], [Price], [quantity], [OrderID], [BookID]) VALUES (16, 1000, 1, 13, 1)
INSERT [dbo].[tblDetail] ([DetailID], [Price], [quantity], [OrderID], [BookID]) VALUES (17, 15000, 1, 14, 4)
INSERT [dbo].[tblDetail] ([DetailID], [Price], [quantity], [OrderID], [BookID]) VALUES (18, 1000, 1, 14, 1)
INSERT [dbo].[tblDetail] ([DetailID], [Price], [quantity], [OrderID], [BookID]) VALUES (19, 2000, 1, 14, 2)
INSERT [dbo].[tblDetail] ([DetailID], [Price], [quantity], [OrderID], [BookID]) VALUES (20, 5500, 1, 14, 3)
INSERT [dbo].[tblDetail] ([DetailID], [Price], [quantity], [OrderID], [BookID]) VALUES (21, 40000, 1, 14, 5)
INSERT [dbo].[tblDetail] ([DetailID], [Price], [quantity], [OrderID], [BookID]) VALUES (22, 4000, 1, 14, 6)
SET IDENTITY_INSERT [dbo].[tblDetail] OFF
SET IDENTITY_INSERT [dbo].[tblOrder] ON 

INSERT [dbo].[tblOrder] ([OrderID], [DateOrder], [total], [UserID]) VALUES (1, CAST(N'2021-07-11' AS Date), 1010, 3)
INSERT [dbo].[tblOrder] ([OrderID], [DateOrder], [total], [UserID]) VALUES (2, CAST(N'2021-07-11' AS Date), 5510, 3)
INSERT [dbo].[tblOrder] ([OrderID], [DateOrder], [total], [UserID]) VALUES (3, CAST(N'2021-07-11' AS Date), 2010, 3)
INSERT [dbo].[tblOrder] ([OrderID], [DateOrder], [total], [UserID]) VALUES (4, CAST(N'2021-07-11' AS Date), 5510, 3)
INSERT [dbo].[tblOrder] ([OrderID], [DateOrder], [total], [UserID]) VALUES (5, CAST(N'2021-07-12' AS Date), 1010, 3)
INSERT [dbo].[tblOrder] ([OrderID], [DateOrder], [total], [UserID]) VALUES (6, CAST(N'2021-07-12' AS Date), 4010, 3)
INSERT [dbo].[tblOrder] ([OrderID], [DateOrder], [total], [UserID]) VALUES (7, CAST(N'2021-07-12' AS Date), 1010, 3)
INSERT [dbo].[tblOrder] ([OrderID], [DateOrder], [total], [UserID]) VALUES (8, CAST(N'2021-07-12' AS Date), 2010, 3)
INSERT [dbo].[tblOrder] ([OrderID], [DateOrder], [total], [UserID]) VALUES (9, CAST(N'2021-07-12' AS Date), 1010, 3)
INSERT [dbo].[tblOrder] ([OrderID], [DateOrder], [total], [UserID]) VALUES (10, CAST(N'2021-07-12' AS Date), 50510, 3)
INSERT [dbo].[tblOrder] ([OrderID], [DateOrder], [total], [UserID]) VALUES (11, CAST(N'2021-07-12' AS Date), 1010, 3)
INSERT [dbo].[tblOrder] ([OrderID], [DateOrder], [total], [UserID]) VALUES (12, CAST(N'2021-07-13' AS Date), 2010, 3)
INSERT [dbo].[tblOrder] ([OrderID], [DateOrder], [total], [UserID]) VALUES (13, CAST(N'2021-07-14' AS Date), 1010, 3)
INSERT [dbo].[tblOrder] ([OrderID], [DateOrder], [total], [UserID]) VALUES (14, CAST(N'2021-07-14' AS Date), 67510, 3)
SET IDENTITY_INSERT [dbo].[tblOrder] OFF
INSERT [dbo].[tblRoles] ([RoleID], [RoleName]) VALUES (N'1', N'Admin')
INSERT [dbo].[tblRoles] ([RoleID], [RoleName]) VALUES (N'2', N'Member')
SET IDENTITY_INSERT [dbo].[tblUsers] ON 

INSERT [dbo].[tblUsers] ([UserID], [UserName], [PassWord], [RoleID]) VALUES (1, N'admin', N'1', N'1')
INSERT [dbo].[tblUsers] ([UserID], [UserName], [PassWord], [RoleID]) VALUES (2, N'admin1', N'1', N'1')
INSERT [dbo].[tblUsers] ([UserID], [UserName], [PassWord], [RoleID]) VALUES (3, N'SE140397', N'1', N'2')
INSERT [dbo].[tblUsers] ([UserID], [UserName], [PassWord], [RoleID]) VALUES (4, N'huutoan1', N'1', N'2')
SET IDENTITY_INSERT [dbo].[tblUsers] OFF
ALTER TABLE [dbo].[tblBook]  WITH CHECK ADD FOREIGN KEY([CategoryID])
REFERENCES [dbo].[tblCategory] ([CategoryID])
GO
ALTER TABLE [dbo].[tblDetail]  WITH CHECK ADD FOREIGN KEY([BookID])
REFERENCES [dbo].[tblBook] ([BookID])
GO
ALTER TABLE [dbo].[tblDetail]  WITH CHECK ADD FOREIGN KEY([OrderID])
REFERENCES [dbo].[tblOrder] ([OrderID])
GO
ALTER TABLE [dbo].[tblOrder]  WITH CHECK ADD FOREIGN KEY([UserID])
REFERENCES [dbo].[tblUsers] ([UserID])
GO
ALTER TABLE [dbo].[tblUsers]  WITH CHECK ADD FOREIGN KEY([RoleID])
REFERENCES [dbo].[tblRoles] ([RoleID])
GO
USE [master]
GO
ALTER DATABASE [TheBookStore] SET  READ_WRITE 
GO
