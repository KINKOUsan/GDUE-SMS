import request from "@/utils/request";

// 查询学生列表数据
export const queryPageApi = (name, majorId, page, pageSize) => {
  let url = `/student?name=${name}&page=${page}&pageSize=${pageSize}`;
  if (majorId !== null && majorId !== undefined) {
    url += `&majorId=${majorId}`;
  }
  return request.get(url);
};

// 根据id查询学生
export const queryByIdApi = (id) => request.get(`/student/${id}`);

// 添加学生
export const addApi = (student) => request.post(`/student`, student);

// 修改学生
export const updateApi = (student) => request.put(`/student`, student);

// 删除学生
export const deleteApi = (id) => request.delete(`/student/${id}`);
