import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IEventPhotos, defaultValue } from 'app/shared/model/event-photos.model';

export const ACTION_TYPES = {
  FETCH_EVENTPHOTOS_LIST: 'eventPhotos/FETCH_EVENTPHOTOS_LIST',
  FETCH_EVENTPHOTOS: 'eventPhotos/FETCH_EVENTPHOTOS',
  CREATE_EVENTPHOTOS: 'eventPhotos/CREATE_EVENTPHOTOS',
  UPDATE_EVENTPHOTOS: 'eventPhotos/UPDATE_EVENTPHOTOS',
  DELETE_EVENTPHOTOS: 'eventPhotos/DELETE_EVENTPHOTOS',
  RESET: 'eventPhotos/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IEventPhotos>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type EventPhotosState = Readonly<typeof initialState>;

// Reducer

export default (state: EventPhotosState = initialState, action): EventPhotosState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_EVENTPHOTOS_LIST):
    case REQUEST(ACTION_TYPES.FETCH_EVENTPHOTOS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_EVENTPHOTOS):
    case REQUEST(ACTION_TYPES.UPDATE_EVENTPHOTOS):
    case REQUEST(ACTION_TYPES.DELETE_EVENTPHOTOS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_EVENTPHOTOS_LIST):
    case FAILURE(ACTION_TYPES.FETCH_EVENTPHOTOS):
    case FAILURE(ACTION_TYPES.CREATE_EVENTPHOTOS):
    case FAILURE(ACTION_TYPES.UPDATE_EVENTPHOTOS):
    case FAILURE(ACTION_TYPES.DELETE_EVENTPHOTOS):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_EVENTPHOTOS_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_EVENTPHOTOS):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_EVENTPHOTOS):
    case SUCCESS(ACTION_TYPES.UPDATE_EVENTPHOTOS):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_EVENTPHOTOS):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {}
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState
      };
    default:
      return state;
  }
};

const apiUrl = 'api/event-photos';

// Actions

export const getEntities: ICrudGetAllAction<IEventPhotos> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_EVENTPHOTOS_LIST,
  payload: axios.get<IEventPhotos>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IEventPhotos> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_EVENTPHOTOS,
    payload: axios.get<IEventPhotos>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IEventPhotos> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_EVENTPHOTOS,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IEventPhotos> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_EVENTPHOTOS,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IEventPhotos> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_EVENTPHOTOS,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
