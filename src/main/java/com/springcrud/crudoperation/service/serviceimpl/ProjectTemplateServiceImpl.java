package com.springcrud.crudoperation.service.serviceimpl;

import com.springcrud.crudoperation.dto.template.MilestoneTemplate;
import com.springcrud.crudoperation.dto.template.SubTaskTemplate;
import com.springcrud.crudoperation.dto.template.TaskTemplate;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

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
        template.setActive(subTask.isActive());
        template.setDeleteFlag(subTask.isDeleteFlag());
        return template;
    }
   /* @Override
    public SuccessResponse<Object> createProjectTemplate(String projectId) {
            SuccessResponse<Object> response = new SuccessResponse<>();

            if (Objects.isNull(projectId)) {
                throw new RuntimeException("Project ID cannot be null");
            }

            Project project = projectRepository.findById(projectId)
                    .orElseThrow(() -> new RuntimeException("Project not found"));

            ProjectTemplate projectTemplate = mapProjectToTemplate(project);

            List<MilestoneTemplate> milestoneTemplateList = milestoneRepository.findByProject(new ObjectId(projectId)).stream()
                    .map(this::mapMilestoneToTemplate)
                    .toList();

            projectTemplate.setMilestone(milestoneTemplateList);
            projectTemplateRepository.save(projectTemplate);

            response.setData(projectTemplate);
            response.setStatusCode(200);
            response.setStatusMesssage("Fetched successfully");
            return response;
        }

        private ProjectTemplate mapProjectToTemplate(Project project) {
            ProjectTemplate template = new ProjectTemplate();
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
            MilestoneTemplate template = new MilestoneTemplate();
            template.setId(milestone.getId());
            template.setName(milestone.getName());
            template.setDescription(milestone.getDescription());
            template.setCreatedAt(milestone.getCreatedAt());
            template.setUpdatedAt(milestone.getUpdatedAt());
            template.setCreatedBy(milestone.getCreatedBy());
            template.setUpdatedBy(milestone.getUpdatedBy());
            template.setActive(milestone.isActive());
            template.setDeleteFlag(milestone.isDeleteFlag());

            List<TaskTemplate> taskTemplateList = taskRepository.findByMilestone(new ObjectId(milestone.getId())).stream()
                    .map(this::mapTaskToTemplate)
                    .toList();

            template.setTasks(taskTemplateList);
            return template;
        }

        private TaskTemplate mapTaskToTemplate(Task task) {
            TaskTemplate template = new TaskTemplate();
            template.setId(task.getId());
            template.setName(task.getName());
            template.setDescription(task.getDescription());
            template.setActive(task.isActive());
            template.setDeleteFlag(task.isDeleteFlag());

            List<SubTaskTemplate> subTaskTemplateList = subTaskRepository.findByTask(new ObjectId(task.getId())).stream()
                    .map(this::mapSubTaskToTemplate)
                    .toList();

            template.setSubTasks(subTaskTemplateList);
            return template;
        }

        private SubTaskTemplate mapSubTaskToTemplate(SubTask subTask) {
            SubTaskTemplate template = new SubTaskTemplate();
            template.setId(subTask.getId());
            template.setName(subTask.getName());
            template.setDescription(subTask.getDescription());
            template.setActive(subTask.isActive());
            template.setDeleteFlag(subTask.isDeleteFlag());
            return template;
        }

    }*/


   /* @Override
    public SuccessResponse<Object> createProjectTemplate(String projectId) {
        SuccessResponse<Object> response = new SuccessResponse<>();
        ProjectTemplate projectTemplate = null;
        try {
            if (Objects.isNull(projectId)){
                throw new RuntimeException("Project ID cannot be null");
            }
            Optional<Project> savedProject = projectRepository.findById(projectId);
            if (savedProject.isEmpty()) {
                throw new RuntimeException("Project not found");
            }
            Project project = savedProject.get();

            projectTemplate = new ProjectTemplate();
            projectTemplate.setId(project.getId());
            projectTemplate.setName(project.getName());
            projectTemplate.setDescription(project.getDescription());
            projectTemplate.setCreatedAt(project.getCreatedAt());
            projectTemplate.setUpdatedAt(project.getUpdatedAt());
            projectTemplate.setCreatedBy(project.getCreatedBy());
            projectTemplate.setUpdatedBy(project.getUpdatedBy());
            projectTemplate.setActive(project.isActive());
            projectTemplate.setDeleteFlag(project.isDeleteFlag());

            List<MilestoneTemplate> milestoneTemplateList = milestoneRepository.findByProject(new ObjectId(projectId)).stream()
                    .map(milestone -> {
                        MilestoneTemplate milestoneTemplate = new MilestoneTemplate();
                        milestoneTemplate.setId(milestone.getId());
                        milestoneTemplate.setName(milestone.getName());
                        milestoneTemplate.setDescription(milestone.getDescription());
                        milestoneTemplate.setCreatedAt(LocalDateTime.now());
                        milestoneTemplate.setUpdatedAt(LocalDateTime.now());
                        milestoneTemplate.setCreatedBy(milestone.getCreatedBy());
                        milestoneTemplate.setUpdatedBy(milestone.getUpdatedBy());
                        milestoneTemplate.setActive(milestone.isActive());
                        milestoneTemplate.setDeleteFlag(milestone.isDeleteFlag());

                        List<TaskTemplate> taskTemplateList = taskRepository.findByMilestone(new ObjectId(milestone.getId())).stream()
                                .map(task -> {
                                    TaskTemplate taskTemplate = new TaskTemplate();
                                    taskTemplate.setId(task.getId());
                                    taskTemplate.setName(task.getName());
                                    taskTemplate.setDescription(task.getDescription());
                                    taskTemplate.setActive(task.isActive());
                                    taskTemplate.setDeleteFlag(task.isDeleteFlag());

                                    List<SubTaskTemplate> subtaskTemplateList = subTaskRepository.findByTask(new ObjectId(task.getId())).stream()
                                            .map(subtask -> {
                                                SubTaskTemplate subtaskTemplate = new SubTaskTemplate();
                                                subtaskTemplate.setId(subtask.getId());
                                                subtaskTemplate.setName(subtask.getName());
                                                subtaskTemplate.setDescription(subtask.getDescription());
                                                subtaskTemplate.setActive(subtask.isActive());
                                                subtaskTemplate.setDeleteFlag(subtask.isDeleteFlag());
                                                return subtaskTemplate;
                                            })
                                            .toList();
                                    taskTemplate.setSubTasks(subtaskTemplateList);
                                    return taskTemplate;
                                })
                                        .toList();

                        milestoneTemplate.setTasks(taskTemplateList);
                        return milestoneTemplate;
                    })
                            .toList();

            projectTemplate.setMilestone(milestoneTemplateList);
            projectTemplateRepository.save(projectTemplate);

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
        response.setData(projectTemplate);
        response.setStatusCode(200);
        response.setStatusMesssage("Fetched successfully");
        return response;

    }*/

// Single method with streams
  /*  @Override
    public SuccessResponse<Object> createProjectTemplate(String projectId) {
        SuccessResponse<Object> response = new SuccessResponse<>();

        if (Objects.isNull(projectId)) {
            throw new RuntimeException("Project ID cannot be null");
        }
        Optional<Project> savedProject = projectRepository.findById(projectId);
        if (savedProject.isEmpty()) {
            throw new RuntimeException("Project not found");
        }
        Project project = savedProject.get();
        ProjectTemplate projectTemplate = modelMapper.map(project, ProjectTemplate.class);
        List<Milestone> milestoneList = milestoneRepository.findByProject(new ObjectId(projectId));
        List<MilestoneTemplate> milestoneTemplateList = milestoneList.stream()
                .map(milestone -> {
                    MilestoneTemplate milestoneTemplate = modelMapper.map(milestone, MilestoneTemplate.class);

                    List<Task> taskList = taskRepository.findByMilestone(new ObjectId(milestone.getId()));
                    List<TaskTemplate> taskTemplateList = taskList.stream()
                            .map(task -> {
                                TaskTemplate taskTemplate = modelMapper.map(task, TaskTemplate.class);
                                List<SubTask> subTaskList = subTaskRepository.findByTask(new ObjectId(task.getId()));
                                List<SubTaskTemplate> subTaskTemplateList = subTaskList.stream()
                                        .map(subTask -> modelMapper.map(subTask, SubTaskTemplate.class))
                                        .toList();
                                taskTemplate.setSubTasks(subTaskTemplateList);
                                return taskTemplate;
                            }).toList();

                    milestoneTemplate.setTasks(taskTemplateList);
                    return milestoneTemplate;
                }).toList();

        projectTemplate.setMilestone(milestoneTemplateList);
        projectTemplateRepository.save(projectTemplate);
        response.setStatusCode(200);
        response.setStatusMesssage("Project details fetched successfully");
        response.setData(projectTemplate);

        return response;
    }*/



}


