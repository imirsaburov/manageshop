import Category from "./components/category/Category";
import Product from "./Product";
import NoComplete from "./NoComplete";
import User from "./components/user/Moderator";

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
    }
];