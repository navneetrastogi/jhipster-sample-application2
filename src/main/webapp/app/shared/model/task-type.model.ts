export interface ITaskType {
  id?: string;
  name?: string;
  priority?: number;
}

export const defaultValue: Readonly<ITaskType> = {};
