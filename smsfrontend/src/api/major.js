import request from "@/utils/request";

// 查询专业列表数据
export const queryPageApi = (name, page, pageSize) => request.get(`/major?name=${name}&page=${page}&pageSize=${pageSize}`);

// 根据id查询专业
export const queryByIdApi = (id) => request.get(`/major/${id}`);

// 添加专业
export const addApi = (major) => request.post("/major", major);

// 修改专业
export const updateApi = (major) => request.put("/major", major);

// 删除专业
export const deleteApi = (id) => request.delete(`/major/${id}`);