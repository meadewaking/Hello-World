from tvtk.api import tvtk
 
def read_data():# 读入数据
    plot3d = tvtk.MultiBlockPLOT3DReader(
            xyz_file_name="combxyz.bin",#网格文件
            q_file_name="combq.bin",#空气动力学结果文件
            scalar_function_number=100,#设置标量数据数量
            vector_function_number=200#设置矢量数据数量
            )
    plot3d.update()
    return plot3d
 
plot3d = read_data()
grid = plot3d.output.get_block(0)

print(grid.dimensions)      #查看模型规模
print(grid.points.to_array())      #查看所有点
print(grid.cell_data.number_of_arrays)      #查看单元数据个数
print(grid.point_data.number_of_arrays)      #查看点数据个数
print(grid.point_data.scalars.name)      #查看scalar的命名
print(grid.point_data.vectors.name)      #查看vector的命名