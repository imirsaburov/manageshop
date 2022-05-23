import React from 'react'
import {Pagination} from "antd";
import {PAGE_SIZE} from "../../Constants";

export default function ({totalItems, onChange, current}) {
    return <Pagination
        current={current + 1}
        onChange={(e) => onChange(e - 1)}
        pageSize={PAGE_SIZE}
        total={totalItems}/>;
}