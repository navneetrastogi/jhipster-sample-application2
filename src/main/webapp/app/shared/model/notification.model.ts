import { Moment } from 'moment';

export const enum NotificationStatus {
  PENDING = 'PENDING',
  DELIVERED = 'DELIVERED',
  READ = 'READ'
}

export interface INotification {
  id?: string;
  type?: string;
  date?: Moment;
  createdOn?: Moment;
  message?: string;
  icon?: string;
  status?: NotificationStatus;
}

export const defaultValue: Readonly<INotification> = {};
