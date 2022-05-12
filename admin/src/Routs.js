import Category from "./Category";
import Product from "./Product";
import NoComplete from "./NoComplete";

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
        component: NoComplete,
    }
];