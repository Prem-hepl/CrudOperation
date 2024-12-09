package com.springcrud.crudoperation.service.serviceimpl;

import com.springcrud.crudoperation.dto.template.*;
import com.springcrud.crudoperation.model.Milestone;
import com.springcrud.crudoperation.model.Project;
import com.springcrud.crudoperation.model.SubTask;
import com.springcrud.crudoperation.model.Task;
import com.springcrud.crudoperation.model.template.ProjectTemplate;
import com.springcrud.crudoperation.repository.*;
import com.springcrud.crudoperation.response.SuccessResponse;
import com.springcrud.crudoperation.service.ProjectTemplateService;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class ProjectTemplateServiceImpl implements ProjectTemplateService {
    @Autowired
    private ProjectTemplateRepository projectTemplateRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MilestoneRepository milestoneRepository;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private SubTaskRepository subTaskRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public SuccessResponse<Object> createProjectTemplate(String projectId) {
        SuccessResponse<Object>response=new SuccessResponse<>();

        if (Objects.isNull(projectId)){
            throw new RuntimeException("Project Id not be null");
        }
        Optional<Project> savedProject=projectRepository.findById(projectId);
        if (savedProject.isEmpty()){
            throw new RuntimeException("Project not found");
        }

        Project project=new Project();

        ProjectTemplate projectTemplate=mapProjectToTemplate(project);

        List<MilestoneTemplate> milestoneTemplateList=milestoneRepository.findByProject(new ObjectId(projectId)).stream()
                .map(this::mapMilestoneToTemplate)
                .toList();
        projectTemplate.setMilestone(milestoneTemplateList);
        projectTemplateRepository.save(projectTemplate);
        response.setData(projectTemplate);
        response.setStatusCode(200);
        return response;
    }
    @Override
    public SuccessResponse<Object> getAllPojectTemplate() {
            SuccessResponse<Object> response = new SuccessResponse<>();
            try {
                List<ProjectTemplate> projectTemplates = projectTemplateRepository.findAll();

                List<ProjectTemplateResponse> projectTemplateList = projectTemplates.stream()
                        .map(projectTemplate -> {
                            ProjectTemplateResponse projectResponse = new ProjectTemplateResponse();
                            projectResponse.setId(projectTemplate.getId());
                            projectResponse.setName(projectTemplate.getName());
                            projectResponse.setDescription(projectTemplate.getDescription());
                            projectResponse.setCreatedAt(projectTemplate.getCreatedAt().toString());
                            projectResponse.setUpdatedAt(projectTemplate.getUpdatedAt().toString());
                            projectResponse.setCreatedBy(projectTemplate.getCreatedBy());
                            projectResponse.setUpdatedBy(projectTemplate.getUpdatedBy());
                            projectResponse.setActive(projectTemplate.isActive());
                            projectResponse.setDeleteFlag(projectTemplate.isDeleteFlag());

                            List<MilestoneTemplateResponse> milestoneResponses = projectTemplate.getMilestone().stream()
                                    .map(milestone -> {
                                        MilestoneTemplateResponse milestoneResponse = new MilestoneTemplateResponse();
                                        milestoneResponse.setId(milestone.getId());
                                        milestoneResponse.setName(milestone.getName());
                                        milestoneResponse.setDescription(milestone.getDescription());
                                        milestoneResponse.setCreatedAt(milestone.getCreatedAt().toString());
                                        milestoneResponse.setUpdatedAt(milestone.getUpdatedAt().toString());
                                        milestoneResponse.setCreatedBy(milestone.getCreatedBy());
                                        milestoneResponse.setUpdatedBy(milestone.getUpdatedBy());
                                        milestoneResponse.setActive(milestone.isActive());
                                        milestoneResponse.setDeleteFlag(milestone.isDeleteFlag());

                                        List<TaskTemplateResponse> taskResponses = milestone.getTasks().stream()
                                                .map(task -> {
                                                    TaskTemplateResponse taskResponse = new TaskTemplateResponse();
                                                    taskResponse.setId(task.getId());
                                                    taskResponse.setName(task.getName());
                                                    taskResponse.setDescription(task.getDescription());
                                                    taskResponse.setCreatedAt(task.getCreatedAt().toString());
                                                    taskResponse.setUpdatedAt(task.getUpdatedAt().toString());
                                                    taskResponse.setCreatedBy(task.getCreatedBy());
                                                    taskResponse.setUpdatedBy(task.getUpdatedBy());
                                                    taskResponse.setActive(task.isActive());
                                                    taskResponse.setDeleteFlag(task.isDeleteFlag());

                                                    List<SubTaskTemplateResponse> subTaskResponses = task.getSubTasks().stream()
                                                            .map(subTask -> {
                                                                SubTaskTemplateResponse subTaskResponse = new SubTaskTemplateResponse();
                                                                subTaskResponse.setId(subTask.getId());
                                                                subTaskResponse.setName(subTask.getName());
                                                                subTaskResponse.setDescription(subTask.getDescription());
                                                                subTaskResponse.setCreatedAt(String.valueOf(subTask.getCreatedAt()));
                                                                subTaskResponse.setUpdatedAt(String.valueOf(subTask.getUpdatedAt()));
                                                                subTaskResponse.setCreatedBy(subTask.getCreatedBy());
                                                                subTaskResponse.setUpdatedBy(subTask.getUpdatedBy());
                                                                subTaskResponse.setActive(subTask.isActive());
                                                                subTaskResponse.setDeleteFlag(subTask.isDeleteFlag());
                                                                return subTaskResponse;
                                                            })
                                                            .toList();
                                                    taskResponse.setSubTasks(subTaskResponses);

                                                    return taskResponse;
                                                })
                                                .toList();
                                        milestoneResponse.setTasks(taskResponses);

                                        return milestoneResponse;
                                    })
                                    .toList();
                            projectResponse.setMilestone(milestoneResponses);

                            return projectResponse;
                        })
                        .toList();
                response.setData(projectTemplateList);
                response.setCount(projectTemplateList.size());
            } catch (Exception e) {
                throw new RuntimeException("Error fetching project templates", e);
            }
        return response;
        }



        private ProjectTemplate mapProjectToTemplate(Project project) {
        ProjectTemplate template=new ProjectTemplate();
        template.setId(project.getId());
        template.setName(project.getName());
        template.setDescription(project.getDescription());
        template.setCreatedAt(project.getCreatedAt());
        template.setUpdatedAt(project.getUpdatedAt());
        template.setCreatedBy(project.getCreatedBy());
        template.setUpdatedBy(project.getUpdatedBy());
        template.setActive(project.isActive());
        template.setDeleteFlag(project.isDeleteFlag());
        return template;
    }
    private MilestoneTemplate mapMilestoneToTemplate(Milestone milestone) {
        MilestoneTemplate template=new MilestoneTemplate();
        template.setId(milestone.getId());
        template.setName(milestone.getName());
        template.setDescription(milestone.getDescription());
        template.setCreatedAt(milestone.getCreatedAt());
        template.setUpdatedAt(milestone.getUpdatedAt());
        template.setCreatedBy(milestone.getCreatedBy());
        template.setUpdatedBy(milestone.getUpdatedBy());
        template.setActive(milestone.isActive());
        template.setDeleteFlag(milestone.isDeleteFlag());

        List<TaskTemplate>taskTemplateList=taskRepository.findByMilestone(new ObjectId(milestone.getId())).stream()
                .map(this::mapTaskToTemplate)
                .toList();
        template.setTasks(taskTemplateList);
        return template;
    }

    private TaskTemplate mapTaskToTemplate(Task task) {
        TaskTemplate template=new TaskTemplate();
        template.setId(task.getId());
        template.setName(task.getName());
        template.setDescription(task.getDescription());
        template.setCreatedAt(task.getCreatedAt());
        template.setUpdatedAt(task.getUpdatedAt());
        template.setCreatedBy(task.getCreatedBy());
        template.setUpdatedBy(task.getUpdatedBy());
        template.setActive(task.isActive());
        template.setDeleteFlag(task.isDeleteFlag());

        List<SubTaskTemplate>subTaskTemplatesList=subTaskRepository.findByTask(new ObjectId(task.getId())).stream()
                .map(this::mapSubtaskToTemplate).toList();
        template.setSubTasks(subTaskTemplatesList);
        return template;
    }

    private SubTaskTemplate mapSubtaskToTemplate(SubTask subTask) {
        SubTaskTemplate template=new SubTaskTemplate();
        template.setId(subTask.getId());
        template.setName(subTask.getName());
        template.setDescription(subTask.getDescription());
        template.setCreatedAt(subTask.getCreatedAt());
        template.setUpdatedAt(subTask.getUpdatedAt());
        template.setCreatedBy(subTask.getCreatedBy());
        template.setUpdatedBy(subTask.getUpdatedBy());
        template.setActive(subTask.isActive());
        template.setDeleteFlag(subTask.isDeleteFlag());
        return template;
    }

}
