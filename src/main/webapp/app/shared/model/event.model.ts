import { Moment } from 'moment';

export interface IEvent {
  id?: string;
  title?: string;
  description?: string;
  eventImageURL?: string;
  eventDate?: Moment;
  createdOn?: Moment;
  lastModifiedOn?: Moment;
}

export const defaultValue: Readonly<IEvent> = {};
