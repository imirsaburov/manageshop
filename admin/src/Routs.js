import Category from "./components/category/Category";
import Product from "./components/product/Product";
import NoComplete from "./NoComplete";
import User from "./components/user/Moderator";
import Size from "./components/size/Size";

export const routes = [
    {
        path: '/category',
        component: Category,
    },
    {
        path: '/product',
        component: Product,
    },
    {
        path: '/user',
        component: User,
    },
    {
        path: '/size',
        component: Size,
    }
];